package ru.grebe.moviescollection.filmlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.filmdomain.usecases.FilmsListUseCase

class FilmsListViewModel : ViewModel(), KoinComponent {

    private var getFilmListJob: Job? = null
    private val _listFilmsState =
        MutableStateFlow<FilmListViewModelState>(FilmListViewModelState.Loading)
    val listFilmsState: StateFlow<FilmListViewModelState> = _listFilmsState

    private val useCase: FilmsListUseCase by inject<FilmsListUseCase>()


    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _listFilmsState.emit(
                    FilmListViewModelState.Error(
                        throwable.localizedMessage ?: throwable.message ?: ""
                    )
                )
            }
        }

    init {
        getFilmList(null)
    }

    fun setCurrentGenre(genre: String) {
        getFilmList(genre)
    }

    fun getFilmList(newGenre: String?) {
        getFilmListJob?.cancel()
        val oldStateSuccess = (listFilmsState.value as? FilmListViewModelState.Success)
        val oldGenre = oldStateSuccess?.selectedGenre
        val currentGenre = if (oldGenre != null && oldGenre == newGenre) {
            null
        } else {
            newGenre
        }

        getFilmListJob = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (oldStateSuccess == null) _listFilmsState.emit(FilmListViewModelState.Loading)
            val filmsModel = useCase.getFilmsList(currentGenre)
            val genreList =
                oldStateSuccess?.genresList ?: filmsModel.map { it.genres }.flatten().toSet()
                    .toList()
            _listFilmsState.emit(
                FilmListViewModelState.Success(
                    genresList = genreList,
                    filmsList = filmsModel,
                    selectedGenre = currentGenre
                )
            )
        }
    }

}
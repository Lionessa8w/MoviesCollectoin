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


    private val coroutineExceptionHandler =
        viewModelScope.launch{
            CoroutineExceptionHandler { _, throwable ->
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
        val oldGenre = (listFilmsState.value as? FilmListViewModelState.Success)?.selectedGenre
        val currentGenre = if (oldGenre != null && oldGenre == newGenre) {
            null
        } else {
            newGenre
        }

        getFilmListJob = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _listFilmsState.emit(FilmListViewModelState.Loading)
            val genresList = useCase.getListGenres()
            val filmsModel = useCase.getFilmsList(currentGenre)
            _listFilmsState.emit(
                FilmListViewModelState.Success(
                    genresList = genresList,
                    filmsList = filmsModel,
                    selectedGenre = currentGenre
                )
            )
        }
    }

}
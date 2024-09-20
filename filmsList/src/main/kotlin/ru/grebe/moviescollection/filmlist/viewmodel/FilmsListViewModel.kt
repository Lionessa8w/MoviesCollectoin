package ru.grebe.moviescollection.filmlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.filmdetails.viewmodel.FilmDetailsViewModelState
import ru.grebe.moviescollection.filmdomain.usecases.FilmsListUseCase

class FilmsListViewModel : ViewModel(), KoinComponent {

    private var getFilmListJob: Job? = null
    private val _listFilmsState =
        MutableLiveData<FilmDetailsViewModelState>(FilmDetailsViewModelState.Loading)
    val listFilmsState: LiveData<FilmDetailsViewModelState> = _listFilmsState

    private var currentGenre: String? = null

    private val useCase: FilmsListUseCase by inject<FilmsListUseCase>()
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            _listFilmsState.postValue(
                FilmDetailsViewModelState.Error(
                    throwable.localizedMessage ?: throwable.message ?: ""
                )
            )
        }

    init {
        getFilmList()
    }

    fun setCurrentGenre(genre: String) {
        currentGenre = genre
        getFilmList()
    }

    fun getFilmList() {
        getFilmListJob?.cancel()

        getFilmListJob = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _listFilmsState.postValue(FilmDetailsViewModelState.Loading)
            val genresList = useCase.getListGenres()
            val filmsModel = useCase.getFilmsList(currentGenre)
            _listFilmsState.postValue(
                FilmDetailsViewModelState.Success(
                    genresList = genresList,
                    filmsList = filmsModel
                )
            )
}
    }

}
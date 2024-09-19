package viewModel

import android.util.Log
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
import usecases.FilmsListUseCase

class FilmsListViewModel : ViewModel(), KoinComponent {

    private var getFilmListJob: Job? = null
    private val _listFilmsState =
        MutableLiveData<FilmListViewModelState>(FilmListViewModelState.Loading)
    val listFilmsState: LiveData<FilmListViewModelState> = _listFilmsState

    private var currentGenre: String? = null

    private val useCase: FilmsListUseCase by inject<FilmsListUseCase>()
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            _listFilmsState.postValue(
                FilmListViewModelState.Error(
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
            _listFilmsState.postValue(FilmListViewModelState.Loading)
            val genresList = useCase.getListGenres()
            val filmsModel = useCase.getFilmsList(currentGenre)
            _listFilmsState.postValue(
                FilmListViewModelState.Success(
                    genresList = genresList,
                    filmsList = filmsModel
                )
            )
}
    }

}
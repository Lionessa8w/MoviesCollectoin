package ru.grebe.moviescollection.filmdetails.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.grebe.moviescollection.filmdetails.model.FilmModelDetailsMapper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.filmdomain.usecases.InfoFilmUseCase

class FilmDetailsViewModel(val id: Int) : ViewModel(), KoinComponent {

    private val _filmsModel =
        MutableStateFlow<FilmDetailsViewModelState>(FilmDetailsViewModelState.Loading)
    val filmsModel: StateFlow<FilmDetailsViewModelState> = _filmsModel
    private var getFilmDetailsJob: Job? = null

    private val useCase: InfoFilmUseCase by inject<InfoFilmUseCase>()

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _filmsModel.emit(
                    FilmDetailsViewModelState.Error(
                        throwable.localizedMessage ?: throwable.message ?: ""
                    )
                )
            }
        }

    init {
        getFilm()
    }

    private fun getFilm() {
        getFilmDetailsJob?.cancel()

        getFilmDetailsJob = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val filmsModelDomain =
                useCase.getFilmInfo(id) ?: throw IllegalStateException("фильм с id = $id не найден")
            _filmsModel.emit(FilmDetailsViewModelState.Success(FilmModelDetailsMapper().invoke(filmsModelDomain)))
        }


    }
}
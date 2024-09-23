package ru.grebe.moviescollection.filmdetails.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.grebe.moviescollection.filmdetails.model.FilmModelDetails
import ru.grebe.moviescollection.filmdetails.model.FilmModelDetailsMapper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.filmdomain.usecases.InfoFilmUseCase

class FilmDetailsViewModel(val id: Int) : ViewModel(), KoinComponent {

    private val _filmsModel = MutableSharedFlow<FilmModelDetails>()
    val filmsModel: SharedFlow<FilmModelDetails> = _filmsModel

    private val useCase: InfoFilmUseCase by inject<InfoFilmUseCase>()

    init {
        getFilm()
    }

    private fun getFilm() {
        viewModelScope.launch(Dispatchers.IO) {
            val filmsModelDomain = useCase.getFilmInfo(id)
            _filmsModel.emit(FilmModelDetailsMapper().invoke(filmsModelDomain))
        }

    }
}
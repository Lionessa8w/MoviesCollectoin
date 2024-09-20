package ru.grebe.moviescollection.filmdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.grebe.moviescollection.filmdetails.model.FilmModelDetails
import ru.grebe.moviescollection.filmdetails.model.FilmModelDetailsMapper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.filmdomain.usecases.InfoFilmUseCase

class InfoFilmViewModel(val id: Int) : ViewModel(), KoinComponent {

    private val _filmsModel = MutableLiveData<FilmModelDetails>()
    val filmsModel: LiveData<FilmModelDetails> = _filmsModel

    private val useCase: InfoFilmUseCase by inject<InfoFilmUseCase>()

    init {
        getFilm()
    }

    private fun getFilm() {
        viewModelScope.launch(Dispatchers.IO) {
            val filmsModelDomain=useCase.getFilmInfo(id)
            _filmsModel.postValue(FilmModelDetailsMapper().invoke(filmsModelDomain))
        }

    }
}
package viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.FilmModelDetails
import model.FilmModelDetailsMapper
import model.FilmModelDomainMapper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import usecases.InfoFilmUseCase

class InfoFilmViewModel(val id: Int) : ViewModel(), KoinComponent {

    private val _filmsModel = MutableLiveData<FilmModelDetails>()
    val filmsModel: LiveData<FilmModelDetails> = _filmsModel

    private val useCase: InfoFilmUseCase by inject<InfoFilmUseCase>()

    init {
        getFilm()
    }

    private fun getFilm() {
        viewModelScope.launch(Dispatchers.IO) {
            val filmModelDomain=FilmModelDomainMapper()
                .invoke(useCase.getFilmInfo(id))
            _filmsModel.postValue(FilmModelDetailsMapper()
                .invoke(filmModelDomain))
        }

    }
}
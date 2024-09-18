package viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.FilmsModel
import usecases.InfoFilmUseCase

class InfoFilmViewModel(private val id: Int) : ViewModel() {

    private val _filmsModel= MutableLiveData<FilmsModel>()
    val filmsModel: LiveData<FilmsModel> = _filmsModel

    private val
            useCase = InfoFilmUseCase()

    init {
        getFilm()
    }

    private fun getFilm() {
        viewModelScope.launch(Dispatchers.IO) {
            _filmsModel.postValue(useCase.getFilmInfo(id))
        }

    }
}
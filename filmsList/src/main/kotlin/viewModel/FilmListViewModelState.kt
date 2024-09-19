package viewModel

import model.FilmsModelDomain


sealed class FilmListViewModelState {
    object Loading : FilmListViewModelState()

    data class Error(val message: String) : FilmListViewModelState()

    class Success(
        val genresList: List<String>,
        val filmsList: List<FilmsModelDomain>
    ) : FilmListViewModelState()
}
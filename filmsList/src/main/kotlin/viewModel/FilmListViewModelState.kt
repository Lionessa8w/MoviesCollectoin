package viewModel


sealed class FilmListViewModelState {
    object Loading : FilmListViewModelState()

    data class Error(val message: String) : FilmListViewModelState()

    class Success(
        val genresList: List<String>,
        val filmsList: List<Unit> // todo change this
    ) : FilmListViewModelState()
}
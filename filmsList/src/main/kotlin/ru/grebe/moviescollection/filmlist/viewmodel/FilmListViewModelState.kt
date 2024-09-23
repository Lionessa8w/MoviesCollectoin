package ru.grebe.moviescollection.filmlist.viewmodel

import ru.grebe.moviescollection.filmdomain.model.FilmsModelDomain

sealed class FilmListViewModelState {
    object Loading : FilmListViewModelState()

    data class Error(val message: String) : FilmListViewModelState()

    class Success(
        val genresList: List<String>,
        val filmsList: List<FilmsModelDomain>,
        val selectedGenre: String?
    ) : FilmListViewModelState()
}
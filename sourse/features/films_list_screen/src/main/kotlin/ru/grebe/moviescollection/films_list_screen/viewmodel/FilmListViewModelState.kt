package ru.grebe.moviescollection.films_list_screen.viewmodel

import ru.grebe.moviescollection.film_domain_api.model.FilmsModelDomain

sealed class FilmListViewModelState {
    data object Loading : FilmListViewModelState()

    data class Error(val message: String) : FilmListViewModelState()

    class Success(
        val genresList: List<String>,
        val filmsList: List<FilmsModelDomain>,
        val selectedGenre: String?
    ) : FilmListViewModelState()
}
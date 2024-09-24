package ru.grebe.moviescollection.film_details_screen.viewmodel

import ru.grebe.moviescollection.film_details_screen.model.FilmModelDetails

sealed class FilmDetailsViewModelState {
    data object Loading : FilmDetailsViewModelState()

    data class Error(val message: String) : FilmDetailsViewModelState()

    class Success(val filmModelDetails: FilmModelDetails) : FilmDetailsViewModelState()
}
package ru.grebe.moviescollection.filmdetails.viewmodel

import ru.grebe.moviescollection.filmdetails.model.FilmModelDetails
import ru.grebe.moviescollection.filmdomain.model.FilmsModelDomain

sealed class FilmDetailsViewModelState {
    object Loading : FilmDetailsViewModelState()

    data class Error(val message: String) : FilmDetailsViewModelState()

    class Success(val filmModelDetails: FilmModelDetails)
        : FilmDetailsViewModelState()
}
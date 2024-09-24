package ru.grebe.moviescollection.film_details_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FilmDetailsViewModelFactory(
    private val id: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilmDetailsViewModel(id) as T
    }
}
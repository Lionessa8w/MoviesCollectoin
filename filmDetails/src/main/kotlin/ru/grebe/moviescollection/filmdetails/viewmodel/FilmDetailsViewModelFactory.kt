package ru.grebe.moviescollection.filmdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FilmDetailsViewModelFactory(private val id: Int)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InfoFilmViewModel(id) as T
    }
}
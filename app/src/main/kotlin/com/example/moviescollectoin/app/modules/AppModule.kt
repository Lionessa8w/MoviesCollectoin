package com.example.moviescollectoin.app.modules


import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import viewModel.FilmsListViewModel
import viewModel.InfoFilmViewModel

//viewModel
val appModule= module {
    viewModel<InfoFilmViewModel>{
        InfoFilmViewModel(
            id = get()
        )
    }
    viewModel<FilmsListViewModel> {
        FilmsListViewModel()
    }
}
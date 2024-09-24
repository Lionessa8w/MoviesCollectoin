package ru.grebe.moviescollection.app.modules


import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.grebe.moviescollection.films_list_screen.viewmodel.FilmsListViewModel

//viewModel
val appModule= module {

    viewModel<FilmsListViewModel> {
        FilmsListViewModel()
    }
}
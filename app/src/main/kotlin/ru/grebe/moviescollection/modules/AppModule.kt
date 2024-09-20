package ru.grebe.moviescollection.modules


import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.grebe.moviescollection.filmlist.viewmodel.FilmsListViewModel
import ru.grebe.moviescollection.filmdetails.viewmodel.InfoFilmViewModel

//viewModel
val appModule= module {
//    viewModel<InfoFilmViewModel>{
//        InfoFilmViewModel(
//            id = get()
//        )
//    }
    viewModel<FilmsListViewModel> {
        FilmsListViewModel()
    }
}
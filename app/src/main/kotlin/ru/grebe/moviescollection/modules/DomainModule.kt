package ru.grebe.moviescollection.modules

import org.koin.dsl.module
import ru.grebe.moviescollection.filmdomain.usecases.FilmsListUseCase
import ru.grebe.moviescollection.filmdomain.usecases.InfoFilmUseCase

// usecases
val domainModule= module {
    factory {

        FilmsListUseCase()
    }
    factory {
        InfoFilmUseCase()
    }
}
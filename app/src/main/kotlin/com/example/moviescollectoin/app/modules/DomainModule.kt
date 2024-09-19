package com.example.moviescollectoin.app.modules

import org.koin.dsl.module
import usecases.FilmsListUseCase
import usecases.InfoFilmUseCase

// usecases
val domainModule= module {
    factory {
        FilmsListUseCase()
    }
    factory {
        InfoFilmUseCase()
    }
}
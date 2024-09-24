package ru.grebe.moviescollection.app.modules

import org.koin.dsl.module
import ru.grebe.moviescollection.film_domain_api.usecases.FilmsListUseCase
import ru.grebe.moviescollection.film_domain_api.usecases.InfoFilmUseCase

// usecases
val domainModule= module {
    factory {

        FilmsListUseCase()
    }
    factory {
        InfoFilmUseCase()
    }
}
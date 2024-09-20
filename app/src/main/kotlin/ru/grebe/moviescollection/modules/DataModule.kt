package ru.grebe.moviescollection.modules

import org.koin.dsl.module
import ru.grebe.moviescollection.filmdata.repositories.FilmsRepository

//репозотории

val dataModule= module {
    single<FilmsRepository> {
        FilmsRepository()
    }
}
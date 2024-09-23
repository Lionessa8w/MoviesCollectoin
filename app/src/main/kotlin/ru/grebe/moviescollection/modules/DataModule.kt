package ru.grebe.moviescollection.modules

import org.koin.dsl.module
import ru.grebe.moviescollection.filmdata.repositories.FilmsRepositoryImpl

//репозотории

val dataModule= module {
    single<FilmsRepositoryImpl> {
        FilmsRepositoryImpl()
    }
}
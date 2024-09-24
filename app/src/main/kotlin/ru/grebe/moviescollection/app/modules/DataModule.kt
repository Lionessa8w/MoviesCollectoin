package ru.grebe.moviescollection.app.modules

import org.koin.dsl.module
import ru.grebe.moviescollection.film_data_api.repositories.FilmsRepositoryImpl

//репозотории

val dataModule= module {
    single<FilmsRepositoryImpl> {
        FilmsRepositoryImpl()
    }
}
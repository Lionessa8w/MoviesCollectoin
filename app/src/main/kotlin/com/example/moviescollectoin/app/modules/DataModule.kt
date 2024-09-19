package com.example.moviescollectoin.app.modules

import org.koin.dsl.module
import repositories.FilmsRepository

//репозотории

val dataModule= module {
    single<FilmsRepository> {
        FilmsRepository()
    }
}
package ru.grebe.moviescollection.filmdata.model

import retrofit2.http.GET

interface FilmsAPI {
    @GET("films.json")
    suspend fun getAllFilmsModel(): FilmsList
}
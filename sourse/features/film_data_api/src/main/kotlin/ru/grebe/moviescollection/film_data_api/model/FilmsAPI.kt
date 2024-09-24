package ru.grebe.moviescollection.film_data_api.model

import retrofit2.http.GET

interface FilmsAPI {
    @GET("films.json")
    suspend fun getAllFilmsModel(): FilmsList
}
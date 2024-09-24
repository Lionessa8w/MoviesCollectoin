package ru.grebe.moviescollection.film_data_api.model

import com.google.gson.annotations.SerializedName

data class FilmsList(

    @SerializedName("films") var films: List<FilmsModel> = arrayListOf()

)
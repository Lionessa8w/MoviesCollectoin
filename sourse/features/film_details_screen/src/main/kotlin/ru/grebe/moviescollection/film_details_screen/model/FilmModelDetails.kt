package ru.grebe.moviescollection.film_details_screen.model

data class FilmModelDetails (
    val id: Int? = null,
    val localizedName: String? = null,
    val name: String? = null,
    val year: Int? = null,
    val rating: Double? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val genres: List<String> = arrayListOf()
)
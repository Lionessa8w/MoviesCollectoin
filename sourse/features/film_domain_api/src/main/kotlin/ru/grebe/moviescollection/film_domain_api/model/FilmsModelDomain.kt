package ru.grebe.moviescollection.film_domain_api.model

data class FilmsModelDomain (
    val id: Int? = null,
    val localizedName: String? = null,
    val name: String? = null,
    val year: Int? = null,
    val rating: Double? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val genres: List<String> = arrayListOf()
)
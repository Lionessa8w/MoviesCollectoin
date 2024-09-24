package ru.grebe.moviescollection.film_data_api.model

import com.google.gson.annotations.SerializedName

data class FilmsModel (

    @SerializedName("id") val id: Int? = null,
    @SerializedName("localized_name") val localizedName: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("year") val year: Int? = null,
    @SerializedName("rating") val rating: Double? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("genres") val genres: List<String> = arrayListOf()

)
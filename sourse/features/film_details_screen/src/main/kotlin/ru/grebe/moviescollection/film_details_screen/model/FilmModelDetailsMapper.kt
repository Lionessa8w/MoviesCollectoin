package ru.grebe.moviescollection.film_details_screen.model

import ru.grebe.moviescollection.film_domain_api.model.FilmsModelDomain

class FilmModelDetailsMapper {

    operator fun invoke(model: FilmsModelDomain): FilmModelDetails {
        return FilmModelDetails(
            id = model.id,
            localizedName = model.localizedName,
            name = model.name,
            year = model.year,
            rating = model.rating,
            imageUrl = model.imageUrl,
            description = model.description,
            genres = model.genres
        )
    }
}
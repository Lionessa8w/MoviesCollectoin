package ru.grebe.moviescollection.filmdetails.model

import ru.grebe.moviescollection.filmdomain.model.FilmsModelDomain

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
package ru.grebe.moviescollection.filmdomain.model

import ru.grebe.moviescollection.filmdata.model.FilmsModel

class FilmModelDomainMapper {

    operator fun invoke(model: FilmsModel): FilmsModelDomain {
        return FilmsModelDomain(
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
package ru.grebe.moviescollection.film_domain_api.model

import ru.grebe.moviescollection.film_data_api.model.FilmsModel

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
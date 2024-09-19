package model

import model.FilmsModel

class FilmModelMapper {

    operator fun invoke(model: FilmsModel): model.FilmsModel {
    return model.FilmsModel(
        id= model.id,
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
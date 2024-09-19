package model

class FilmModelDomainMapper {

    operator fun invoke(model: FilmsModel): model.FilmsModelDomain {
    return FilmsModelDomain(
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
package model

class FilmListModelMapper {
    operator fun invoke(model: FilmsList): FilmsListModel{
        return FilmsListModel(
            films = model.films
        )
    }
}
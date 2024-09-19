package usecases

import model.FilmsModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repositories.FilmsRepository


class FilmsListUseCase: KoinComponent {

    val repository: FilmsRepository by inject<FilmsRepository>()

    // получаем cписок фильмов по жанру
    suspend fun getFilmsList(genre: String?): List<FilmsModel> {
        return repository.getFilmsByGenre(genre)
    }

    //список жанров функция
    suspend fun getListGenres(): List<String> {
        return repository.getListGenres()
    }


}
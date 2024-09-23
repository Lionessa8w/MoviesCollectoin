package ru.grebe.moviescollection.filmdomain.usecases

import ru.grebe.moviescollection.filmdomain.model.FilmModelDomainMapper
import ru.grebe.moviescollection.filmdomain.model.FilmsModelDomain
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.filmdata.repositories.FilmsRepositoryImpl


class FilmsListUseCase: KoinComponent {

    val repository: FilmsRepositoryImpl by inject<FilmsRepositoryImpl>()

    // получаем cписок фильмов по жанру
    suspend fun getFilmsList(genre: String?): List<FilmsModelDomain> {
        val filmModel=repository.getFilmsByGenre(genre)
        return filmModel.map { FilmModelDomainMapper().invoke(it) }
    }

    //список жанров функция
    suspend fun getListGenres(): List<String> {
        return repository.getListGenres()
    }


}
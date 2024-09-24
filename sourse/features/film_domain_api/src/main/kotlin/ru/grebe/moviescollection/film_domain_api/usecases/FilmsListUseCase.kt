package ru.grebe.moviescollection.film_domain_api.usecases

import ru.grebe.moviescollection.film_domain_api.model.FilmModelDomainMapper
import ru.grebe.moviescollection.film_domain_api.model.FilmsModelDomain
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.film_data_api.repositories.FilmsRepositoryImpl


class FilmsListUseCase : KoinComponent {

    val repository: FilmsRepositoryImpl by inject<FilmsRepositoryImpl>()

    // получаем cписок фильмов по жанру
    suspend fun getFilmsList(genre: String?): List<FilmsModelDomain> {
        val filmModel = repository.getFilmsByGenre(genre)
        return filmModel.map { FilmModelDomainMapper().invoke(it) }
    }

}
package ru.grebe.moviescollection.film_domain_api.usecases

import ru.grebe.moviescollection.film_domain_api.model.FilmModelDomainMapper
import ru.grebe.moviescollection.film_domain_api.model.FilmsModelDomain
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.film_data_api.repositories.FilmsRepositoryImpl

class InfoFilmUseCase : KoinComponent {
    private val repository: FilmsRepositoryImpl by inject<FilmsRepositoryImpl>()

    // получаем инфо о фильме по id
    suspend fun getFilmInfo(id: Int): FilmsModelDomain? {
        val filmsModel = repository.getFilmInfo(id)
        val mapper = FilmModelDomainMapper()
        return filmsModel?.let { mapper(it) }
    }
}
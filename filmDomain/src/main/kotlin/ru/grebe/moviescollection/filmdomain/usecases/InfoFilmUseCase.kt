package ru.grebe.moviescollection.filmdomain.usecases

import ru.grebe.moviescollection.filmdomain.model.FilmModelDomainMapper
import ru.grebe.moviescollection.filmdomain.model.FilmsModelDomain
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.filmdata.repositories.FilmsRepositoryImpl

class InfoFilmUseCase : KoinComponent {
    private val repository: FilmsRepositoryImpl by inject<FilmsRepositoryImpl>()

    // получаем инфо о фильме по id
    suspend fun getFilmInfo(id: Int): FilmsModelDomain {
        val filmsModel = repository.getFilmInfo(id)
        val mapper = FilmModelDomainMapper()
        return mapper.invoke(filmsModel)
    }
}
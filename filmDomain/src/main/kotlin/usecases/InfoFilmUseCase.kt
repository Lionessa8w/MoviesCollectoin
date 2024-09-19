package usecases

import model.FilmsModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repositories.FilmsRepository

class InfoFilmUseCase: KoinComponent {
    private val repository: FilmsRepository by inject<FilmsRepository>()

    // получаем инфо о фильме по id
    suspend fun getFilmInfo(id: Int): FilmsModel {
        return repository.getFilmInfo(id)
    }
}
package usecases

import model.FilmsModel
import repositories.FilmsRepository

class InfoFilmUseCase {
    private val repository = FilmsRepository.getInstanse()

    suspend fun getFilmInfo(id: Int): FilmsModel {
        return repository.getFilmInfo(id)
    }
}
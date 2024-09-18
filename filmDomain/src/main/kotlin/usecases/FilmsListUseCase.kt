package usecases

import model.FilmsModel
import models.FilmModel
import repositories.FilmsRepository
import room.FilmState

class FilmsListUseCase {
    private val repository = FilmsRepository.getInstanse()

    // получаем cписок фильмов по жанру
    suspend fun getFilmsList(genre: String?): List<FilmsModel> {
        if (genre == "любимые") {
            return repository.getLikeFilms()
        }
        if (genre == "неинтересно") {
            return repository.getIgnoreFilms()
        }
        return repository.getFilmsByGenre(genre)
    }

    //список жанров функция
    suspend fun getListGenres(): List<String> {
        return repository.getListGenres()
    }

    // функция добавления фильма в бд
    suspend fun addFilmLike(id: Int) {
        repository.addFilmLike(id)
    }

    suspend fun addFilmIgnore(id: Int) {
        repository.addFilmIgnore(id)
    }

    suspend fun deletedFilm(id: Int) {
        repository.deletedFilm(id)
    }

    suspend fun isLiked(id: Int): Boolean {
        val entity = repository.getIdFilm(id)
        if (entity != null) {
            return entity.filmState == FilmState.FAVORITE
        }
        return false
    }

    suspend fun setLikeOrDelete(id: Int) {
        if (isLiked(id)) {
            deletedFilm(id)
        } else {
            addFilmLike(id)
        }
    }

    suspend fun isIgnore(id: Int): Boolean {
        val entity = repository.getIdFilm(id)
        if (entity != null) {
            return entity.filmState == FilmState.IGNORE
        }
        return false
    }

    suspend fun setIgnore(id: Int) {
        if (isIgnore(id)) {
            deletedFilm(id)
        } else {
            addFilmIgnore(id)
        }
    }

}
package ru.grebe.moviescollection.film_data_api.repositories

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.grebe.moviescollection.film_data_api.model.FilmsAPI
import ru.grebe.moviescollection.film_data_api.model.FilmsModel

/**  парсинг jsonFile **/
class FilmsRepositoryImpl {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/").build()

    private val filmsApi = retrofit.create(FilmsAPI::class.java)

    /** получаем список всех фильмов **/
    private suspend fun getFullFilmsList(): List<FilmsModel> {
        return filmsApi.getAllFilmsModel().films
    }

    /** получаем список фильмов по жанру **/
    suspend fun getFilmsByGenre(genre: String?): List<FilmsModel> {
        if (genre == null) return getFullFilmsList()
        return getFullFilmsList().filter { it.genres.contains(genre) }
    }

    /** получить фильм по id */
    suspend fun getFilmInfo(id: Int): FilmsModel? {
        return getFullFilmsList().firstOrNull() { it.id == id }
    }
}
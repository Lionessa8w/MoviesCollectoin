package repositories

import android.util.Log
import com.google.gson.GsonBuilder
import model.FilmsAPI
import model.FilmsModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Загрузка успешна"

// парсинг jsonFile
class FilmsRepository private constructor() {

    private var filmsListParseJson = listOf<FilmsModel>()
    private var listGenres = listOf<String>()

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/").build()

    private val filmsApi = retrofit.create(FilmsAPI::class.java)

    //получаем весь список моделей фильмов
    private suspend fun getFullFilmsList(): List<FilmsModel> {
        // получили список всех фильмов
        if (filmsListParseJson.isEmpty()) {
            filmsListParseJson = filmsApi.getAllFilmsModel().films
        }
        return filmsListParseJson
    }

    suspend fun getFilmsByGenre(genre: String?): List<FilmsModel> {
        if (genre == null) return getFullFilmsList()
        return getFullFilmsList().filter { it.genres.contains(genre) }
    }


    //получить список жанров
    suspend fun getListGenres(): List<String> {
        listGenres =
            getFullFilmsList().map { filmsModel -> filmsModel.genres }.flatten().toSet().toList()

        return listGenres
    }

    suspend fun getFilmInfo(id: Int): FilmsModel {
        return getFullFilmsList().first { it.id == id }

    }

//    suspend fun getIdFilm(id: Int): FilmsStateEntity? {
//        return filmListDao.getIdFilms(id.toString()).firstOrNull()
//    }

    companion object {
        private var INSTANSE: FilmsRepository? = null

        fun getInstanse(): FilmsRepository {
            return synchronized(this) {
                val currentInstanse = INSTANSE ?: FilmsRepository()
                INSTANSE = currentInstanse
                currentInstanse
            }
        }
    }



}
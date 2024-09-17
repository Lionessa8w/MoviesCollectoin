package repositories

import android.util.Log
import com.google.gson.GsonBuilder
import model.FilmsAPI
import model.FilmsModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import room.BdHolder
import room.FilmState
import room.FilmsStateEntity

private const val TAG = "Загрузка успешна"

// парсинг jsonFile
class FilmsRepository private constructor() {

    private var filmsListParseJson = listOf<FilmsModel>()
    private var listGenres = listOf<String>()
    private val listGenreLike = listOf("любимые", "неинтересно")
    private val filmListDao = BdHolder.getInstance().getDatabase().filmListIdDao()

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
        Log.d(TAG, "$filmsListParseJson")
        val listBd = filmListDao.getAll()
        val likeList = listBd.filter { it.filmState == FilmState.FAVORITE }.map { it.id }
        val ignoreList = listBd.filter { it.filmState == FilmState.IGNORE }.map { it.id }
        val currentFilmsListParseJson = filmsListParseJson
        currentFilmsListParseJson.forEach {
            val isFavorite = likeList.contains(it.id.toString())
            it.isLiked = isFavorite
        }
        currentFilmsListParseJson.forEach {
            val isIgnoreFilms = ignoreList.contains(it.id.toString())
            it.isIgnore = isIgnoreFilms
        }


        return currentFilmsListParseJson
    }

    suspend fun getFilmsByGenre(genre: String?): List<FilmsModel> {
        if (genre == null) return getFullFilmsList()
        return getFullFilmsList().filter { it.genres.contains(genre) }
    }

    //список любимых фильмов
    suspend fun getLikeFilms(): List<FilmsModel> {
        return getFullFilmsList().filter { it.isLiked }

    }

    suspend fun getIgnoreFilms(): List<FilmsModel> {
        return getFullFilmsList().filter { it.isIgnore }
    }

    //получить список жанров
    suspend fun getListGenres(): List<String> {
        listGenres =
            getFullFilmsList().map { filmsModel -> filmsModel.genres }.flatten().plus(listGenreLike)
                .toSet().toList()

        return listGenres
    }

    suspend fun getFilmInfo(id: Int): FilmsModel {
        return getFullFilmsList().first { it.id == id }

    }

    // функция добавления фильма в бд
    suspend fun addFilmLike(id: Int) {
        filmListDao.insertNewId(FilmsStateEntity(id.toString(), FilmState.FAVORITE))
    }

    suspend fun addFilmIgnore(id: Int) {
        filmListDao.insertNewId(FilmsStateEntity(id.toString(), FilmState.IGNORE))
    }

    suspend fun deletedFilm(id: Int) {
        filmListDao.deletedIdFilm(id.toString())
    }

    suspend fun getIdFilm(id: Int): FilmsStateEntity? {
        return filmListDao.getIdFilms(id.toString()).firstOrNull()
    }


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
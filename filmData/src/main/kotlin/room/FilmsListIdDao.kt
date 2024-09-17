package room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface FilmsListIdDao {
    @Query("SELECT * FROM film_state_entity")
    fun getAll(): List<FilmsStateEntity>

    @Query("DELETE FROM film_state_entity WHERE id = :id")
    fun deletedIdFilm(id: String)

    @Insert(entity = FilmsStateEntity::class)
    fun insertNewId(filmsStateEntity: FilmsStateEntity)

    @Query("SELECT * FROM film_state_entity WHERE id = :id")
    fun getIdFilms(id: String): List<FilmsStateEntity>



}

@Database(
    version = 1,
    entities = [FilmsStateEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmListIdDao(): FilmsListIdDao
}
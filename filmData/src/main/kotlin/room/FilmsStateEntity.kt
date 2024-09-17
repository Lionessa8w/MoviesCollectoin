package room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_state_entity")
data class FilmsStateEntity(@PrimaryKey val id: String,
    @ColumnInfo(name="filmState") val filmState:FilmState) {
}

enum class FilmState{
    DEFAULT,
    FAVORITE,
    IGNORE
}
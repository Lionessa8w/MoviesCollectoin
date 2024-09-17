package room

import android.content.Context
import androidx.room.Room
import java.lang.IllegalStateException


class BdHolder private constructor() {
    private var database: AppDatabase? = null

    fun init(context: Context) {
        if (database!= null) return
         database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "word_database"
        ).build()


    }

    fun getDatabase(): AppDatabase {
        return database ?: throw IllegalStateException("Необходимо инициализировать БД")
    }

    companion object {
        private var INSTANCE: BdHolder? = null
        fun getInstance(): BdHolder {
            return synchronized(this) {
                val currentInstanse = INSTANCE ?: BdHolder()
                INSTANCE = currentInstanse
                currentInstanse
            }
        }
    }
}
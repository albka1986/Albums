package ua.com.ponomarenko.albums.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.com.ponomarenko.albums.data.db.dao.AlbumDao
import ua.com.ponomarenko.albums.data.db.entity.Album

/**
 * Created by Ponomarenko Oleh on 7/15/2019.
 */

@Database(entities = [Album::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object {
        @Volatile
        private var instance: AlbumDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AlbumDatabase::class.java, "albums.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}
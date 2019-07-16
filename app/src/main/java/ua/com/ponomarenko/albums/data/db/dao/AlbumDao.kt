package ua.com.ponomarenko.albums.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.com.ponomarenko.albums.data.db.entity.Album

/**
 * Created by Ponomarenko Oleh on 7/16/2019.
 */
@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(albums: List<Album>)

    @Query("SELECT * FROM album ORDER by title ASC")
    fun getAllAlbums(): LiveData<List<Album>?>
}
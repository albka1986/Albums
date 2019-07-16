package ua.com.ponomarenko.albums.data.repository

import androidx.lifecycle.LiveData
import ua.com.ponomarenko.albums.data.db.entity.Album

interface AppRepository {
    suspend fun getAllAlbums(): LiveData<List<Album>?>
}
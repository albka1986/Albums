package ua.com.ponomarenko.albums.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.ponomarenko.albums.data.db.dao.AlbumDao
import ua.com.ponomarenko.albums.data.db.entity.Album
import ua.com.ponomarenko.albums.data.network.AlbumNetworkManger
import ua.com.ponomarenko.albums.data.network.response.ResponseAlbums

class AppRepositoryImpl(
    private val albumNetworkManager: AlbumNetworkManger,
    private val albumDao: AlbumDao
) : AppRepository {

    init {
        albumNetworkManager.allAlbums.observeForever { newAlbums ->
            newAlbums?.let { persistFetchedAlbums(it) }
        }
    }

    private fun persistFetchedAlbums(albums: List<ResponseAlbums>) {
        GlobalScope.launch(Dispatchers.IO) {
            val albumEntities = albums.map { it.toEntry() }
            albumDao.upsert(albumEntities)
        }
    }

    override suspend fun getAllAlbums(): LiveData<List<Album>?> {
        return withContext(Dispatchers.IO) {
            initAlbums()
            return@withContext albumDao.getAllAlbums()
        }

    }

    private suspend fun initAlbums() {
        albumNetworkManager.fetchAllAlbums()
    }
}
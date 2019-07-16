package ua.com.ponomarenko.albums.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ua.com.ponomarenko.albums.data.db.entity.Album
import ua.com.ponomarenko.albums.data.network.response.ResponseAlbums

class AlbumNetworkMangerImpl(private val retrofitService: RetrofitService) : AlbumNetworkManger {

    companion object {
        private const val TAG = "AlbumNetworkManger"
    }

    private val _fetchedAlbums = MutableLiveData<List<ResponseAlbums>>()

    override val allAlbums: LiveData<List<ResponseAlbums>?>
        get() = _fetchedAlbums

    override suspend fun fetchAllAlbums() {
        try {
            val response = retrofitService.getAlbumsAsync().await()
            if (response.isSuccessful) {
                _fetchedAlbums.postValue(response.body())
            } else {
                Log.e(TAG, response.message())
            }
        } catch (e: Exception) {
            Log.e(TAG, e.localizedMessage)
        }
    }
}
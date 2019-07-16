package ua.com.ponomarenko.albums.data.network

import androidx.lifecycle.LiveData
import ua.com.ponomarenko.albums.data.network.response.ResponseAlbums

/**
 * Created by Ponomarenko Oleh on 7/16/2019.
 */
interface AlbumNetworkManger {

    val allAlbums: LiveData<List<ResponseAlbums>?>

    suspend fun fetchAllAlbums()
}
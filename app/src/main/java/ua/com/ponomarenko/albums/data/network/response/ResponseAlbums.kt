package ua.com.ponomarenko.albums.data.network.response

import ua.com.ponomarenko.albums.data.db.entity.Album


data class ResponseAlbums(
    val id: Int,
    val userId: Int,
    val title: String
) {
    fun toEntry(): Album {
        return Album(id, userId, title)
    }
}


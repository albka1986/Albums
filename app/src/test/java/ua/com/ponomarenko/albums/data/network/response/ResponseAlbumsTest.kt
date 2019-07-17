package ua.com.ponomarenko.albums.data.network.response

import org.junit.Assert
import org.junit.Test
import ua.com.ponomarenko.albums.data.db.entity.Album

/**
 * Created by Ponomarenko Oleh on 7/17/2019.
 */
class ResponseAlbumsTest {

    @Test
    fun initTest() {
        val responseAlbums = ResponseAlbums(1, 1, "Good Album")
        Assert.assertNotNull(responseAlbums)
        Assert.assertNotNull(responseAlbums.id)
        Assert.assertNotNull(responseAlbums.userId)
        Assert.assertNotNull(responseAlbums.title)
        Assert.assertTrue("Good Album" == responseAlbums.title)
        Assert.assertTrue("New Album" != responseAlbums.title)

    }

    @Test
    fun convertMethod() {
        val album = ResponseAlbums(2, 3, "Some Title")
        val entryAlbum = album.toEntry()
        assert(entryAlbum.javaClass.isAssignableFrom(Album::class.java))
        Assert.assertEquals(2, entryAlbum.id)
        Assert.assertEquals(3, entryAlbum.userId)
        Assert.assertEquals("Some Title", entryAlbum.title)


    }

}
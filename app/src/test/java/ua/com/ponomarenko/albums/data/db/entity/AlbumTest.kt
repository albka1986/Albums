package ua.com.ponomarenko.albums.data.db.entity

import org.junit.Assert
import org.junit.Test

/**
 * Created by Ponomarenko Oleh on 7/17/2019.
 */
class AlbumTest {
    @Test
    fun initTest() {
        val album = Album(1, 1, "New Title")
        Assert.assertNotNull(album)
        Assert.assertNotNull(album.id)
        Assert.assertNotNull(album.userId)
        Assert.assertNotNull(album.title)
        Assert.assertTrue("New Title" == album.title)
        Assert.assertTrue("Other title" != album.title)
    }
}
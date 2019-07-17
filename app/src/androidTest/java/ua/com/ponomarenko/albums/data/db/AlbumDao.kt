package ua.com.ponomarenko.albums.data.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import ua.com.ponomarenko.albums.data.db.dao.AlbumDao
import ua.com.ponomarenko.albums.data.db.entity.Album

/**
 * Created by Ponomarenko Oleh on 7/17/2019.
 */
@RunWith(AndroidJUnit4::class)
class AlbumDao : KodeinAware {

    override val kodein: Kodein by closestKodein(InstrumentationRegistry.getInstrumentation().targetContext)
    private val albumDao: AlbumDao by instance()

    @Test
    fun getAllAlbums() {
        CoroutineScope(Dispatchers.Default).launch {
            val allAlbums = albumDao.getAllAlbums()
            Assert.assertNotNull(allAlbums.value != null)
        }
    }

    @Test
    fun insertAlbum() {
        CoroutineScope(Dispatchers.Default).launch {
            val allAlbums = albumDao.getAllAlbums().value
            Assert.assertNotNull(allAlbums != null)
            val currentSize = allAlbums!!.size
            val newAlbum = Album(5, 5, "testAlbum")
            albumDao.upsert(listOf(newAlbum))

            val updatedAlbumList = albumDao.getAllAlbums().value
            Assert.assertNotNull(updatedAlbumList != null)
            val updatedSize = updatedAlbumList!!.size
            Assert.assertEquals(currentSize + 1, updatedSize)
        }
    }


}
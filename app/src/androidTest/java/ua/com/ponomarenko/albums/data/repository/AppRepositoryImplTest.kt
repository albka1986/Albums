package ua.com.ponomarenko.albums.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import ua.com.ponomarenko.albums.data.network.AlbumNetworkManger

/**
 * Created by Ponomarenko Oleh on 7/17/2019.
 */

@RunWith(AndroidJUnit4::class)
class AppRepositoryImplTest : KodeinAware {

    override val kodein: Kodein by closestKodein(InstrumentationRegistry.getInstrumentation().targetContext)
    private val albumNetworkManager by instance<AlbumNetworkManger>()

    @Test
    fun getAllAlbums() {
        CoroutineScope(Dispatchers.IO).launch {
            albumNetworkManager.fetchAllAlbums()
        }
    }
}
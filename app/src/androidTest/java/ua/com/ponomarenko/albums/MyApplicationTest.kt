package ua.com.ponomarenko.albums

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import ua.com.ponomarenko.albums.data.db.AlbumDatabase
import ua.com.ponomarenko.albums.data.db.dao.AlbumDao
import ua.com.ponomarenko.albums.data.network.AlbumNetworkManger
import ua.com.ponomarenko.albums.data.network.RetrofitService
import ua.com.ponomarenko.albums.data.repository.AppRepository
import ua.com.ponomarenko.albums.ui.base.ViewModelFactory

/**
 * Created by Ponomarenko Oleh on 7/17/2019.
 */

@RunWith(AndroidJUnit4::class)
class MyApplicationTest : KodeinAware {

    override val kodein: Kodein by closestKodein(InstrumentationRegistry.getInstrumentation().targetContext)

    @Test
    fun testInitialization() {
        val retrofitService: RetrofitService by instance()
        val albumDatabase: AlbumDatabase by instance()
        val albumDao: AlbumDao by instance()
        val albumNetworkManger: AlbumNetworkManger by instance()
        val appRepository: AppRepository by instance()
        val viewModelFactory: ViewModelFactory by instance()

        Assert.assertNotNull(retrofitService)
        Assert.assertNotNull(albumDatabase)
        Assert.assertNotNull(albumDao)
        Assert.assertNotNull(albumNetworkManger)
        Assert.assertNotNull(appRepository)
        Assert.assertNotNull(viewModelFactory)


    }
}
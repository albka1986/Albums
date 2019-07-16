package ua.com.ponomarenko.albums

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ua.com.ponomarenko.albums.data.db.AlbumDatabase
import ua.com.ponomarenko.albums.data.network.AlbumNetworkManger
import ua.com.ponomarenko.albums.data.network.AlbumNetworkMangerImpl
import ua.com.ponomarenko.albums.data.network.RetrofitService
import ua.com.ponomarenko.albums.data.repository.AppRepository
import ua.com.ponomarenko.albums.data.repository.AppRepositoryImpl
import ua.com.ponomarenko.albums.ui.base.ViewModelFactory

/**
 * Created by Ponomarenko Oleh on 7/16/2019.
 */
class MyApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton { RetrofitService() }
        bind() from singleton { AlbumDatabase(instance()) }
        bind() from singleton { instance<AlbumDatabase>().albumDao() }
        bind<AlbumNetworkManger>() with singleton { AlbumNetworkMangerImpl(instance()) }
        bind<AppRepository>() with singleton {
            AppRepositoryImpl(instance(), instance())
        }
        bind() from provider { ViewModelFactory(instance()) }
    }

}
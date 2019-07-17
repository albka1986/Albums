package ua.com.ponomarenko.albums.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import ua.com.ponomarenko.albums.data.repository.AppRepository

/**
 * Created by Ponomarenko Oleh on 7/17/2019.
 */
@RunWith(AndroidJUnit4::class)
class MainViewModelTest : KodeinAware {

    override val kodein: Kodein by closestKodein(InstrumentationRegistry.getInstrumentation().targetContext)
    private lateinit var viewModel: MainViewModel
    private val repository: AppRepository by instance()

    @Before
    fun initViewModel(){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel = MainViewModel(repository)
        }
    }


    @Test
    fun getAlbums() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = viewModel.albums.await()
            response.observeForever { t ->
                assert(t != null)
                assert(t!!.size>1)
            }
        }
    }
}
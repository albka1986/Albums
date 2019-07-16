package ua.com.ponomarenko.albums.ui

import androidx.lifecycle.ViewModel
import ua.com.ponomarenko.albums.data.repository.AppRepository
import ua.com.ponomarenko.albums.utils.lazyDeferred

/**
 * Created by Ponomarenko Oleh on 7/16/2019.
 */
class MainViewModel(private val repository: AppRepository) : ViewModel() {

    val albums by lazyDeferred {
        repository.getAllAlbums()
    }

}
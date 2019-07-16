package ua.com.ponomarenko.albums.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import ua.com.ponomarenko.albums.R
import ua.com.ponomarenko.albums.ui.adapter.AlbumAdapter
import ua.com.ponomarenko.albums.ui.base.ScopedActivity
import ua.com.ponomarenko.albums.ui.base.ViewModelFactory

class MainActivity : ScopedActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: ViewModelFactory by instance()
    private lateinit var viewModel: MainViewModel
    private val adapter: AlbumAdapter by lazy { AlbumAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        initRecyclerView()
        bindUi()
    }

    private fun initRecyclerView() {
        recycler_view.adapter = adapter
    }

    private fun bindUi() = launch {
        val albums = viewModel.albums.await()
        albums.observe(this@MainActivity, Observer {
            adapter.updateData(it)
        })
    }
}

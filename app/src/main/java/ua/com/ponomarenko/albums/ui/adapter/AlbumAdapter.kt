package ua.com.ponomarenko.albums.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.album_item.view.*
import ua.com.ponomarenko.albums.R
import ua.com.ponomarenko.albums.data.db.entity.Album

/**
 * Created by Ponomarenko Oleh on 7/16/2019.
 */
class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var list: ArrayList<Album> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].apply {
            holder.userName.text = userId.toString()
            holder.title.text = title
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(newList: List<Album>?) {
        list.clear()
        newList?.let { list.addAll(it) }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.user_name_tv
        val title: TextView = view.title_tv
    }
}
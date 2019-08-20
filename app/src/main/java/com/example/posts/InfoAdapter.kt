package com.example.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import kotlinx.android.synthetic.main.item_album.view.*
import kotlinx.android.synthetic.main.item_autor.view.*
import kotlinx.android.synthetic.main.item_header.view.tv_title

class InfoAdapter (var albums : ArrayList<Album>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val AUTOR_INFO_TYPE = 0
    private val ALBUM_TYPE = 1
    private val counts: MutableList<Int> = emptyList<Int>().toMutableList()
    var autor : Autor? = null

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> AUTOR_INFO_TYPE
            else -> ALBUM_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            AUTOR_INFO_TYPE -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_autor, parent, false)
                return AutorHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
                return AlbumHolder(v)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (albums != null) {
            albums!!.size + 1
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AutorHolder -> if (autor != null) holder.bindAutor(autor!!)
            is AlbumHolder -> if (counts.isNotEmpty()) {
                holder.bindAlbum(albums!![position] as Album, counts[position - 1])
            } else {
                holder.bindAlbum(albums!![position] as Album)
            }

        }
    }

    fun setCounts(counts: List<Int>) {
        this.counts.addAll(counts)
    }


    class AutorHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindAutor(autor: Autor){
            itemView.tv_name.text = autor.name
            itemView.tv_username.text = autor.username
            itemView.tv_email.text = autor.email
        }
    }

    class AlbumHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindAlbum(album: Album){
            itemView.tv_title.text = album.title
        }

        fun bindAlbum(album: Album, counts: Int){
            itemView.tv_title.text = album.title
            itemView.tv_photo_count.text = counts.toString() + " Photo"
        }
    }
}
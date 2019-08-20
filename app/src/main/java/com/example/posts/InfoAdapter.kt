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

class InfoAdapter  : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    class Info(
        val title: String,
        val count: String
    )
    var albums : MutableList<Info> = emptyList<Info>().toMutableList()
    private val AUTOR_INFO_TYPE = 0
    private val ALBUM_TYPE = 1
    private val counts: MutableList<Int> = emptyList<Int>().toMutableList()
    private var autor : Autor? = null

    fun setAutor(autor : Autor?){
        this.autor = autor
        notifyDataSetChanged()
    }

    fun setInfo(list: List<Info>){
        albums.clear()
        albums.addAll(list)
        notifyDataSetChanged()
    }
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

    override fun getItemCount(): Int = albums.size + 1


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AutorHolder -> if (autor != null) holder.bindAutor(autor!!)
            is AlbumHolder -> holder.bindAlbum(albums[position - 1])
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

        fun bindAlbum(info : Info){
            itemView.tv_title.text = info.title
            itemView.tv_photo_count.text = info.count
        }
    }
}
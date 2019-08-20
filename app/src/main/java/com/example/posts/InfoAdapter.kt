package com.example.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.RowType
import kotlinx.android.synthetic.main.item_album.view.*
import kotlinx.android.synthetic.main.item_autor.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_header.view.tv_title

class InfoAdapter (var listType : ArrayList<RowType>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val counts: MutableList<Int> = emptyList<Int>().toMutableList()

    override fun getItemViewType(position: Int): Int {
        if (listType == null) {
            return -1
        }
        return when (listType!![position]) {
            is Autor -> RowType.AUTOR_INFO_TYPE
            is Album -> RowType.ALBUM_TUPE
            else -> -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (listType != null) {
            when (viewType) {
                RowType.AUTOR_INFO_TYPE -> {
                    val v = LayoutInflater.from(parent.context).inflate(R.layout.item_autor, parent, false)
                    return AutorHolder(v)
                }
                RowType.ALBUM_TUPE -> {
                    val v = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
                    return AlbumHolder(v)
                }
            }
        }
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_autor, parent, false)
        return AutorHolder(v)
    }

    override fun getItemCount(): Int {
        return if (listType != null) {
            listType!!.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (listType != null) {
            when(holder){
                is AutorHolder -> holder.bindAutor(listType!![position] as Autor)
                is AlbumHolder ->{
                    if (counts.isNotEmpty()){
                        holder.bindAlbum(listType!![position] as Album, counts[position - 1])
                    }else{
                        holder.bindAlbum(listType!![position] as Album)
                    }
                }
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
package com.example.posts.repo

import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import io.reactivex.functions.Consumer

interface InfoRepo {

    fun getAutorOfApi(id : Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>)
    fun getAlbumsOfApi(userId : Int, consumer: Consumer<List<Album>>, errorConsumer: Consumer<Throwable>)
    fun getPhotosOfApi(albumId : Int, consumer: Consumer<List<Photo>>, errorConsumer: Consumer<Throwable>)
    fun destroy()
    fun create()
}
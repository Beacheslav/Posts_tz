package com.example.posts.repo

import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import io.reactivex.functions.Consumer

interface InfoRepo {
    fun getAutor(id : Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>)
    fun getAlbums(userId : Int, consumer: Consumer<List<Album>>, errorConsumer: Consumer<Throwable>)
    fun getPhotos(albumId : Int, consumer: Consumer<List<Photo>>, errorConsumer: Consumer<Throwable>)
}
package com.example.posts.repo

import com.example.posts.ApiSomaku
import com.example.posts.InfoAdapter
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class InfoRepoImplemented(val api : ApiSomaku) : InfoRepo {

    override fun getAlbums(userId: Int, consumer: Consumer<List<Album>>, errorConsumer: Consumer<Throwable>) {
        api.getAlbums(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getPhotos(albumId: Int, consumer: Consumer<List<Photo>>, errorConsumer: Consumer<Throwable>) {
        api.getPhotos(albumId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getAutor(id: Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>) {
        api.getAutor(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }
}
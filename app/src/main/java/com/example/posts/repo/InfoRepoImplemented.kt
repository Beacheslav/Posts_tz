package com.example.posts.repo

import com.example.posts.ApiSomaku
import com.example.posts.InfoAdapter
import com.example.posts.db.AlbumDao
import com.example.posts.db.AutorDao
import com.example.posts.db.PhotoDao
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class InfoRepoImplemented(val api : ApiSomaku, val albumDao : AlbumDao, val photoDao: PhotoDao, val autorDao: AutorDao) : InfoRepo {

    //API
    override fun getAlbumsOfApi(userId: Int, consumer: Consumer<List<Album>>, errorConsumer: Consumer<Throwable>) {
        api.getAlbums(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getPhotosOfApi(albumId: Int, consumer: Consumer<List<Photo>>, errorConsumer: Consumer<Throwable>) {
        api.getPhotos(albumId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getAutorOfApi(id: Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>) {
        api.getAutor(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    //BD
    override fun getAlbumsOfDb(userId: Int, consumer: Consumer<List<Album>>, errorConsumer: Consumer<Throwable>) {
        albumDao.equalsId(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getPhotosOfBd(albumId: Int, consumer: Consumer<List<Photo>>, errorConsumer: Consumer<Throwable>) {
        photoDao.equalsId(albumId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getAutorOfBd(id: Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>) {
        autorDao.equalsId(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }
}
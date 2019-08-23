package com.example.posts.repo

import com.example.posts.ApiSomaku
import com.example.posts.db.AlbumDao
import com.example.posts.db.AutorDao
import com.example.posts.db.PhotoDao
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class InfoRepoImplemented(val api : ApiSomaku, val albumDao : AlbumDao, val photoDao: PhotoDao, val autorDao: AutorDao) : InfoRepo {

    val disposable = CompositeDisposable()

    //API
    override fun getAlbumsOfApi(userId: Int, consumer: Consumer<List<Album>>, errorConsumer: Consumer<Throwable>) {

        disposable.add(Single.zip(
            albumDao.equalsId(userId)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io()),
            api.getAlbums(userId)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    albumDao.equalsId(userId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(consumer, errorConsumer)
                    errorConsumer.accept(it)
                    ArrayList()

                },
            BiFunction<List<Album>, List<Album>, List<Album>> { fromDao, fromApi ->
                if (fromApi.isNotEmpty()) {
                    albumDao.insertAll(fromApi)
                }
                val list = emptyList<Album>().toMutableList().apply {
                    addAll(fromDao)
                    addAll(fromApi)
                }
                val clearList = list.distinctBy {
                    it.id
                }
                clearList
            }
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer))
//        disposable.add(api.getAlbums(userId)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(consumer, errorConsumer))
    }

    override fun getPhotosOfApi(albumId: Int, consumer: Consumer<List<Photo>>, errorConsumer: Consumer<Throwable>) {

        disposable.add(Single.zip(
            photoDao.equalsId(albumId)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io()),
            api.getPhotos(albumId)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    photoDao.equalsId(albumId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(consumer, errorConsumer)
                    ArrayList()

                },
            BiFunction<List<Photo>, List<Photo>, List<Photo>> { fromDao, fromApi ->
                if (fromApi.isNotEmpty()) {
                    photoDao.insertAll(fromApi)
                }
                val list = emptyList<Photo>().toMutableList().apply {
                    addAll(fromDao)
                    addAll(fromApi)
                }
                val clearList = list.distinctBy {
                    it.id
                }
                clearList
            }
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer))
//        disposable.add(api.getPhotos(albumId)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(consumer, errorConsumer))
    }

    override fun getAutorOfApi(id: Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>) {

        disposable.add(Single.zip(
            autorDao.equalsId(id)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io()),
            api.getAutor(id)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    autorDao.equalsId(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(consumer, errorConsumer)
                    errorConsumer.accept(it)
                    ArrayList()

                },
            BiFunction<List<Autor>, List<Autor>, List<Autor>> { fromDao, fromApi ->
                if (fromApi.isNotEmpty()) {
                    autorDao.insertAll(fromApi)
                }
                val list = emptyList<Autor>().toMutableList().apply {
                    addAll(fromDao)
                    addAll(fromApi)
                }
                val clearList = list.distinctBy {
                    it.id
                }
                clearList
            }
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer))
    }

    //BD
    override fun getAlbumsOfDb(userId: Int, consumer: Consumer<List<Album>>, errorConsumer: Consumer<Throwable>) {
        disposable.add(albumDao.equalsId(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer))
    }

    override fun getPhotosOfBd(albumId: Int, consumer: Consumer<List<Photo>>, errorConsumer: Consumer<Throwable>) {
        disposable.add(photoDao.equalsId(albumId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer))
    }

    override fun getAutorOfBd(id: Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>) {
        disposable.add(autorDao.equalsId(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer))
    }

    override fun  destroy(){
        disposable.dispose()
        disposable.clear()
    }
}
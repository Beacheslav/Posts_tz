package com.example.posts.info

import com.example.posts.ApiSomaku
import com.example.posts.InfoAdapter
import com.example.posts.models.Album
import com.example.posts.models.Autor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class InfoPresenter : InfoContract.Presenter {

    public var mView: InfoContract.View? = null
    private var mAlbums: ArrayList<Album>? = null
    private var mAutor: Autor? = null

    override fun loadAutor(userId: Int?) {
        if (userId == null) return
        val apiSomaku = ApiSomaku.create()
        apiSomaku.getAutor(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result != null) {
                    val resp = result as ArrayList<Autor>
                    if (resp.isNotEmpty()) {
                        mAutor = resp[0]
                    }
                    mView?.showAutor(mAutor)
                    loadListAlbum(mAutor?.id)
                }
            }, { error ->
                mView?.showLoadError()
                error.printStackTrace()
            })
    }

    override fun loadListAlbum(userId: Int?) {
        if (userId == null) return

        val apiSomaku = ApiSomaku.create()
        apiSomaku.getAlbums(userId)

            .toObservable()
            .doOnNext {
                mAlbums = it
            }
            .flatMapIterable {
                it
            }
            .map {
                InfoAdapter.Info(it.title, " Loding...")
            }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                mView?.showList(result)
                loadPhotos()
            }, { error ->
                mView?.showLoadError()
                error.printStackTrace()
            })
    }

    private fun loadPhotos() {
        val apiSomaku = ApiSomaku.create()
        val o = Observable.fromIterable(ArrayList(mAlbums))
            .map {
                it.id
            }
            .flatMapSingle {
                apiSomaku.getPhotos(it)
            }
            .map {
                it.size
            }
            .zipWith(Observable.fromIterable(mAlbums),
                BiFunction<Int, Album, InfoAdapter.Info> { t1, album ->
                    InfoAdapter.Info(album.title, "${t1} Photos")
                })
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                mView?.showList(it)
            }, {
                it.printStackTrace()
            })
    }

    override fun loadPhotos(id: Int) {
//        val apiSomaku = ApiSomaku.create()
//        apiSomaku.getPhotos(id)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe({ result ->
//                if (result != null) {
//                    mPhotos = result as ArrayList<Photo>
//                    mView?.updatePhotoCount(mPhotos!!.size, id)
//                    mPhotos = null
//                }
//            }, { error ->
//                mView?.showLoadError()
//                error.printStackTrace()
//            })
    }

    override fun start() {
        return
    }
}
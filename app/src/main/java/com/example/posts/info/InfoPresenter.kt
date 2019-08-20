package com.example.posts.info

import android.util.Log
import com.example.posts.ApiSomaku
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
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
                    mView?.updateListUi(mAlbums, mAutor)
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
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result != null) {
                    mAlbums = result as ArrayList<Album>
                    Log.i("loadListAlbum ", mAlbums!!.count().toString())
                    mView?.updateListUi(mAlbums, mAutor)
                    loadPhotos()
                }
            }, { error ->
                mView?.showLoadError()
                error.printStackTrace()
            })
    }

    private fun loadPhotos() {
        Log.i("loadPhotos ", "vnutri")
        val apiSomaku = ApiSomaku.create()
        Log.i("loadPhotos ", mAlbums.toString())
        val o = Observable.fromIterable(ArrayList(mAlbums))
            .map {
                it.id
                Log.i("loadPhotos id: ", it.id.toString())
            }
            .flatMapSingle {
                apiSomaku.getPhotos(it)
            }
            .map {
                it.size
                Log.i("loadPhotos id: ", it.size.toString())
            }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                mView?.showCounts(it)
                Log.i("loadPhotos ", it.count().toString())
                Log.i("loadPhotos mview", mView.toString())
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
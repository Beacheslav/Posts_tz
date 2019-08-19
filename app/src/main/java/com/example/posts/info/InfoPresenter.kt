package com.example.posts.info

import com.example.posts.ApiSomaku
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InfoPresenter : InfoContract.Presenter {

    public var mView: InfoContract.View? = null
    private var mAlbums: ArrayList<Album>? = null
    private var mAutor: Autor? = null
    private var mPhotos: ArrayList<Photo>? = null

    override fun loadAutor(userId: Int?) {
        if (userId == null) return

        val apiSomaku = ApiSomaku.create()
        apiSomaku.getAutor(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result != null) {
                    val resp = result as ArrayList<Autor>
                    if (resp.size != 0) {
                        val mAutor = resp[0]
                        mView?.updateListUi(mAlbums, mAutor)
                    } else {
                        mView?.updateListUi(mAlbums, null)
                    }

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
                    mView?.updateListUi(mAlbums, mAutor)

                    for (album in mAlbums!!){
                        loadPhotos(album.id)
                    }

                }
            }, { error ->
                mView?.showLoadError()
                error.printStackTrace()
            })
    }

    override fun loadPhotos(id: Int) {
        val apiSomaku = ApiSomaku.create()
        apiSomaku.getPhotos(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result != null) {
                    mPhotos = result as ArrayList<Photo>
                    mView?.updatePhotoCount(mPhotos!!.size, id)
                }
            }, { error ->
                mView?.showLoadError()
                error.printStackTrace()
            })
    }

    override fun start() {
        return
    }
}
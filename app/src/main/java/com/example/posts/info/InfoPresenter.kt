package com.example.posts.info

import com.example.posts.ApiSomaku
import com.example.posts.models.Album
import com.example.posts.models.Autor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InfoPresenter(view: InfoContract.View) : InfoContract.Presenter {

    private val mView = view
    private var mAlbums: ArrayList<Album>? = null
    private var mAutor: Autor? = null

    init {
        mView.setPresenter(this)
    }

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
                        mView.updateListUi(mAlbums, mAutor)
                    } else {
                        mView.updateListUi(mAlbums, null)
                    }

                }
            }, { error ->
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
                    mView.updateListUi(mAlbums, mAutor)
                }
            }, { error ->
                error.printStackTrace()
            })
    }

    override fun start() {
        if (!mView.isActive()) return
    }
}
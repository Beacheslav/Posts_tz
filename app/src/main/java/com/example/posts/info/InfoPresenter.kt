package com.example.posts.info

import com.example.posts.ApiSomaku
import com.example.posts.models.Album
import com.example.posts.models.Autor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        apiSomaku.getAutor(userId).enqueue(object : Callback<ArrayList<Autor>> {
            override fun onFailure(call: Call<ArrayList<Autor>>?, t: Throwable?) {
                mView.showLoadError()
            }

            override fun onResponse(call: Call<ArrayList<Autor>>?, response: Response<ArrayList<Autor>>?) {
                if (response != null) {
                    val resp = response.body() as ArrayList<Autor>
                    if (resp.size != 0){
                        val mAutor = resp[0]
                        mView.updateListUi(mAlbums, mAutor)
                    } else {
                        mView.updateListUi(mAlbums, null)
                    }

                }
            }
        })
    }

    override fun loadListAlbum(userId : Int?) {
        if (userId == null) return

        val apiSomaku = ApiSomaku.create()
        apiSomaku.getAlbums(userId).enqueue(object : Callback<ArrayList<Album>> {
            override fun onFailure(call: Call<ArrayList<Album>>?, t: Throwable?) {
                mView.showLoadError()
            }

            override fun onResponse(call: Call<ArrayList<Album>>?, response: Response<ArrayList<Album>>?) {
                if (response != null) {
                    mAlbums = response.body() as ArrayList<Album>
                    mView.updateListUi(mAlbums, mAutor)
                }
            }
        })
    }

    override fun start() {
        if (!mView.isActive()) return
    }
}
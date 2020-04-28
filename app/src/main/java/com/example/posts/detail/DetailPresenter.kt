package com.example.posts.detail

import com.example.posts.ApiSomaku
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(view: DetailContract.View) : DetailContract.Presenter {

    private val mView = view //todo  проверка на нуль
    private var mComments : ArrayList<Comment>? = null
    private var mAutor : Autor? = null
    private var mPost : Post? = null

    init {
        mView.setPresenter(this)
    }
    override fun loadComments(post : Post) {

        val apiSomaku = ApiSomaku.create()
        apiSomaku.getComments(post.id).enqueue(object : Callback<ArrayList<Comment>> {
            override fun onFailure(call: Call<ArrayList<Comment>>?, t: Throwable?) {
                //
            }

            override fun onResponse(call: Call<ArrayList<Comment>>?, response: Response<ArrayList<Comment>>?) {
                if (response != null) {
                    mPost = post
                    mComments = response.body() as ArrayList<Comment>
                    mView.updateListUi(mComments, mPost, mAutor)
                }
            }
        })
    }

    override fun loadAutor(id: Int) {
        val apiSomaku = ApiSomaku.create()
        apiSomaku.getAutor(id).enqueue(object : Callback<ArrayList<Autor>> {
            override fun onFailure(call: Call<ArrayList<Autor>>?, t: Throwable?) {
//                mView.showLoadError()
            }

            override fun onResponse(call: Call<ArrayList<Autor>>?, response: Response<ArrayList<Autor>>?) {
                if (response != null) {
                    val listAutor = response.body() as ArrayList<Autor>
                    mAutor = listAutor[0]
                    mView.updateListUi(mComments, mPost, mAutor)
                }
            }
        })
    }

    override fun start() {
        if (!mView.isActive()) return
        //mView.initAdapter()
    }
}
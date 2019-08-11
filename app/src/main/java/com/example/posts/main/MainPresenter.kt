package com.example.posts.main

import com.example.posts.ApiSomaku
import com.example.posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(view: MainContract.View) : MainContract.Presenter {

    private val mView = view
    lateinit var mPosts: ArrayList<Post>

    init {
        mView.setPresenter(this)
    }
    override fun loadList() {

        val apiSomaku = ApiSomaku.create()
        apiSomaku.getPosts().enqueue(object : Callback<ArrayList<Post>>{
            override fun onFailure(call: Call<ArrayList<Post>>?, t: Throwable?) {
                mView.showLoadError()
            }

            override fun onResponse(call: Call<ArrayList<Post>>?, response: Response<ArrayList<Post>>?) {
                if (response != null) {
                    mPosts = response.body() as ArrayList<Post>
                    mView.updateListUi(mPosts)
                }
            }
        })
    }

    override fun start() {
        if (!mView.isActive()) return
        //mView.initAdapter()
    }
}
package com.example.posts.main

import com.example.posts.ApiSomaku
import com.example.posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(view: MainContract.View) : MainContract.Presenter {
    private val mView = view //todo  проверка на нуль
    lateinit var mPosts: ArrayList<Post>

    init {
        mView.setPresenter(this)
    }
    override fun loadList() {
        mView.showProgressBar()

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


//        if(code == 401) mView.showLoadError()
//        if (code == 200) {
//            mView.updateUi()
//
//        }
        mView.hideProgressBar()
        mView.showLoadError()
    }

    override fun start() {
        if (!mView.isActive()) return
        //mView.initAdapter()
    }
}
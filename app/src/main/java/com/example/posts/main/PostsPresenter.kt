package com.example.posts.main

import com.example.posts.ApiSomaku
import com.example.posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsPresenter : PostsContract.Presenter {

    public var mView : PostsContract.View? = null
    lateinit var mPosts: ArrayList<Post>

    override fun itemClick(position: Int) {
        val item = mPosts[position]
        mView?.showPost(item)
    }

    override fun loadList() {

        val apiSomaku = ApiSomaku.create()
        apiSomaku.getPosts().enqueue(object : Callback<ArrayList<Post>>{
            override fun onFailure(call: Call<ArrayList<Post>>?, t: Throwable?) {
                mView?.showLoadError()
            }

            override fun onResponse(call: Call<ArrayList<Post>>?, response: Response<ArrayList<Post>>?) {
                if (response != null) {
                    mPosts = response.body() as ArrayList<Post>
                    val copy = ArrayList(mPosts)
                    copy.forEach {
                        it.title.replace("\n", " ")
                        it.body.replace("\n", " ")
                    }
                    mView?.updateListUi(copy)

                }
            }
        })
    }

    override fun start() {

    }
}
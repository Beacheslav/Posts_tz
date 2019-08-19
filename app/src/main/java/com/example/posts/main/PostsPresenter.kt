package com.example.posts.main

import android.util.Log
import com.example.posts.ApiSomaku
import com.example.posts.models.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

        apiSomaku.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result ->
                if (result != null) {
                    mPosts = result as ArrayList<Post>
                    val copy = ArrayList(mPosts)
                    copy.forEach {
                        it.title.replace("\n", " ")
                        it.body.replace("\n", " ")
                    }
                    mView?.updateListUi(copy)

                }
            }, { error ->
                error.printStackTrace()
            })
    }

    override fun start() {

    }
}
package com.example.posts.main

import com.example.posts.models.Post
import com.example.posts.repo.PostRepo
import io.reactivex.functions.Consumer
import javax.inject.Inject

class PostsPresenter @Inject constructor (val postRepo: PostRepo) : PostsContract.Presenter {

    public var mView : PostsContract.View? = null
    lateinit var mPosts: List<Post>

    override fun itemClick(position: Int) {
        val item = mPosts[position]
        mView?.showPost(item)
    }

    override fun loadList() {
        postRepo.getPostsOfApi(Consumer{
            if (it != null) {
                mPosts = it
                val copy = ArrayList(mPosts)
                copy.forEach {
                    it.title.replace("\n", " ")
                    it.body.replace("\n", " ")
                }
                mView?.updateListUi(copy)
            }
        }, Consumer{
            it.printStackTrace()
        })
    }
}
package com.example.posts.posts

import com.example.posts.App
import com.example.posts.models.Post
import com.example.posts.repo.PostRepo
import io.reactivex.exceptions.CompositeException
import io.reactivex.functions.Consumer
import moxy.InjectViewState
import moxy.MvpPresenter
import java.net.UnknownHostException
import javax.inject.Inject

@InjectViewState
class PostsPresenter @Inject constructor(var postRepo: PostRepo) : MvpPresenter<PostsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadList()
    }

    lateinit var mPosts: List<Post>

    fun itemClick(position: Int) {
        val item = mPosts[position]
        viewState.showPost(item)
    }

    fun loadList() {
        postRepo.getPosts(Consumer {
            if (it != null) {
                mPosts = it
                val copy = ArrayList(mPosts)
                copy.forEach {
                    it.title.replace("\n", " ")
                    it.body.replace("\n", " ")
                }
                viewState.updateListUi(copy)
            }
        }, Consumer {
            if (it is UnknownHostException || it is CompositeException) {
                viewState.showLoadError()
            }
            it.printStackTrace()
        })
    }
}
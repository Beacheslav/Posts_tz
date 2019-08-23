package com.example.posts.detail

import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post
import com.example.posts.repo.CommentsRepo
import io.reactivex.functions.Consumer
import javax.inject.Inject

class CommentsPresenter @Inject constructor(val commentsRepo: CommentsRepo) : CommentsContract.Presenter {

    public var mView : CommentsContract.View? = null
    private var mComments : ArrayList<Comment>? = null
    private var mAutor : Autor? = null
    private var mPost : Post? = null

    override fun itemClick(id: Int) {
        mView?.showAlbums(id)
    }

    override fun loadComments(post : Post) {

        commentsRepo.getComments(post.id, Consumer {
            mPost = post
            mComments = it as ArrayList<Comment>
            mView?.updateListUi(mComments, mAutor)
        }, Consumer {
            mView?.showLoadError()
            it.printStackTrace()
        })
    }

    override fun loadAutor(id: Int) {

        commentsRepo.getAutor(id, Consumer {
            if (it.isEmpty()) return@Consumer
            val listAutor = it as ArrayList<Autor>
            mAutor = listAutor[0]
            mView?.updateListUi(mComments, mAutor)
        }, Consumer {
            mView?.showLoadError()
            it.printStackTrace()
        })
    }
}
package com.example.posts.detail

import com.example.posts.ApiSomaku
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommentsPresenter : CommentsContract.Presenter {

    public var mView : CommentsContract.View? = null
    private var mComments : ArrayList<Comment>? = null
    private var mAutor : Autor? = null
    private var mPost : Post? = null

    override fun itemClick(id: Int) {
        mView?.showAlbums(id)
    }

    override fun loadComments(post : Post) {

        val apiSomaku = ApiSomaku.create()
        apiSomaku.getComments(post.id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result != null) {
                    mPost = post
                    mComments = result as ArrayList<Comment>
                    mView?.updateListUi(mComments, mAutor)
                }
            }, { error ->
                error.printStackTrace()
            })
    }

    override fun loadAutor(id: Int) {

        val apiSomaku = ApiSomaku.create()
        apiSomaku.getAutor(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result != null) {
                    val listAutor = result as ArrayList<Autor>
                    mAutor = listAutor[0]
                    mView?.updateListUi(mComments, mAutor)
                }
            }, { error ->
                error.printStackTrace()
            })
    }

    override fun start() {
        return
    }
}
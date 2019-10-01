package com.example.posts.detail

import com.example.posts.App
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post
import com.example.posts.repo.CommentsRepo
import io.reactivex.functions.Consumer
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class DetailPresenter : MvpPresenter<DetailView>(){

    @Inject
    lateinit var commentsRepo: CommentsRepo

    private var mComments : ArrayList<Comment>? = null
    private var mAutor : Autor? = null
    private var mPost : Post? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.presenterComponent.injectCommentsRepo(this)
        viewState.loadScreen()
    }

     fun itemClick(id: Int) {
         viewState.showAlbums(id)
    }

    fun loadComments(post : Post) {

        commentsRepo.getComments(post.id, Consumer {
            mPost = post
            mComments = it as ArrayList<Comment>
            viewState.updateListUi(mComments, mAutor)
        }, Consumer {
            viewState.showLoadError()
            it.printStackTrace()
        })
    }

    fun loadAutor(id: Int) {

        commentsRepo.getAutor(id, Consumer {
            if (it.isEmpty()) return@Consumer
            val listAutor = it as ArrayList<Autor>
            mAutor = listAutor[0]
            viewState.updateListUi(mComments, mAutor)
        }, Consumer {
            viewState.showLoadError()
            it.printStackTrace()
        })
    }

}
package com.example.posts.detail

import com.example.posts.base.BasePresenter
import com.example.posts.base.BaseView
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post

interface DetailContract {

    interface Presenter : BasePresenter {

        fun loadComments(post : Post)

        fun loadAutor(id : Int)

    }

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun updateListUi(list : ArrayList<Comment>?, post : Post?, autor: Autor?)

        fun showLoadError()

    }
}
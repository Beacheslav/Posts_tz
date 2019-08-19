package com.example.posts.detail

import com.example.posts.base.BasePresenter
import com.example.posts.base.BaseView
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post
import com.example.posts.models.RowType

interface DetailContract {

    interface Presenter : BasePresenter {

        fun loadComments(post: Post)

        fun loadAutor(id : Int)

    }

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun updateListUi(listType : ArrayList<Comment>?, autor: Autor?)

        fun showLoadError()

    }
}
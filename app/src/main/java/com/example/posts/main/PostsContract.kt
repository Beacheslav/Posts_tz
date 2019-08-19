package com.example.posts.main

import com.example.posts.base.BasePresenter
import com.example.posts.base.BaseView
import com.example.posts.models.Post

interface PostsContract {
    interface Presenter : BasePresenter {

        fun loadList()
        fun itemClick(position: Int)

    }

    interface View : BaseView<Presenter>{

        fun showProgressBar()

        fun hideProgressBar()

        fun updateUi()

        fun updateListUi(list: ArrayList<Post>)

        fun showLoadError()
        fun showPost(item: Post)

    }
}
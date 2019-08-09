package com.example.posts.main

import com.example.posts.base.BasePresenter
import com.example.posts.base.BaseView
import com.example.posts.models.Post

interface MainContract {
    interface Presenter : BasePresenter {

        fun loadList()

    }

    interface View : BaseView<Presenter>{

        fun showProgressBar()

        fun hideProgressBar()

        fun updateUi()

        fun updateListUi(list: ArrayList<Post>)

        fun showLoadError()

    }
}
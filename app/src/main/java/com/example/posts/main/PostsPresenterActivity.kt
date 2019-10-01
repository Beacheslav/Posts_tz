package com.example.posts.main

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class PostsPresenterActivity : MvpPresenter<PostsViewActivity>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showFragment()
    }
}
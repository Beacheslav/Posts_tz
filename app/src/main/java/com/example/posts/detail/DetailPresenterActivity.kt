package com.example.posts.detail

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailPresenterActivity : MvpPresenter<DetailViewActivity>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showFragment()
    }
}
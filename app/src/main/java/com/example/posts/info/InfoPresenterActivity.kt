package com.example.posts.info

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class InfoPresenterActivity : MvpPresenter<InfoViewActivity>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showFragment()
    }
}
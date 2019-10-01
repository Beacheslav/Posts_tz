package com.example.posts.detail

import com.example.posts.models.Autor
import com.example.posts.models.Comment
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun loadScreen()

    @StateStrategyType(SkipStrategy::class)
    fun showAlbums(userId : Int)

    @StateStrategyType(SkipStrategy::class)
    fun updateListUi(listType : ArrayList<Comment>?, autor: Autor?)

    @StateStrategyType(SkipStrategy::class)
    fun showLoadError()
}
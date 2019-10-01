package com.example.posts.info

import com.example.posts.InfoAdapter
import com.example.posts.models.Autor
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface InfoView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun loadScreen()

    @StateStrategyType(SkipStrategy::class)
    fun updatePhotoCount(count: Int, id : Int)

    @StateStrategyType(SkipStrategy::class)
    fun showLoadError()

    @StateStrategyType(SkipStrategy::class)
    fun showList(list : List<InfoAdapter.Info>?)

    @StateStrategyType(SkipStrategy::class)
    fun showAutor(mAutor: Autor?)
}
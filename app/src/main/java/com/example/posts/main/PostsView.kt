package com.example.posts.main

import com.example.posts.models.Post
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PostsView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateListUi(list: ArrayList<Post>)

    @StateStrategyType(SkipStrategy::class)
    fun showLoadError()

    @StateStrategyType(SkipStrategy::class)
    fun showPost(item: Post)
}
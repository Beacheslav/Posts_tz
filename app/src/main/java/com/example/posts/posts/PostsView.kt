package com.example.posts.posts

import com.example.posts.models.Post
import moxy.MvpView
import moxy.viewstate.strategy.*

interface PostsView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: Int)

    @StateStrategyType(AddToEndStrategy::class)
    fun updateListUi(list: ArrayList<Post>)

    @StateStrategyType(SkipStrategy::class)
    fun showLoadError()

    @StateStrategyType(SkipStrategy::class)
    fun showPost(item: Post)
}
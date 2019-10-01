package com.example.posts.main

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PostsViewActivity : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun showFragment()
}
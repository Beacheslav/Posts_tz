package com.example.posts.detail

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailViewActivity : MvpView{
    @StateStrategyType(SkipStrategy::class)
    fun showFragment()
}
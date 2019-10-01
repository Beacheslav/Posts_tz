package com.example.posts.info

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface InfoViewActivity : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun showFragment()
}
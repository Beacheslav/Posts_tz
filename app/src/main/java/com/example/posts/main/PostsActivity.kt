package com.example.posts.main

import android.os.Bundle
import com.example.posts.R
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class PostsActivity : MvpAppCompatActivity(), PostsViewActivity {

    @InjectPresenter
    lateinit var mPresenter: PostsPresenterActivity

    override fun showFragment() {
        val fragment = PostsFragment.getInstance()
        supportFragmentManager.beginTransaction().replace(
            R.id.contentFrame,
            fragment
        ).commitNowAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }
}
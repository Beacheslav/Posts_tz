package com.example.posts.detail

import android.os.Bundle
import com.example.posts.R
import com.example.posts.models.Post
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class DetailActivity : MvpAppCompatActivity(), DetailViewActivity {

    @InjectPresenter
    lateinit var mPresenter: DetailPresenterActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }

    override fun showFragment() {
        val data = intent.extras
        val post = data?.getParcelable<Post>("post") ?: return
        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, DetailFragment.getInstance(post)).commitNowAllowingStateLoss()
    }
}
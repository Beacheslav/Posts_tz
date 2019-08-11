package com.example.posts.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posts.R
import com.example.posts.models.Post

class DetailActivity : AppCompatActivity() {

    lateinit var mView: DetailFragment
    lateinit var  mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.extras
        val post = data.getParcelable<Post>("post")

        setContentView(R.layout.activity_fragment)
        mView = DetailFragment.getInstance(post)
        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, mView).commitNow()
        mPresenter = DetailPresenter(mView)
    }
}
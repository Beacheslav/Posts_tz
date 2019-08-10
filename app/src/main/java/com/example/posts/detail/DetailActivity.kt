package com.example.posts.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posts.R
import com.example.posts.models.Post

class DetailActivity : AppCompatActivity() {

    private val mView: DetailFragment = DetailFragment.getInstance()
    val mPresenter: DetailPresenter = DetailPresenter(mView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, mView).commitNow()

        val data = intent.extras
        val post = data.getParcelable<Post>("post")

        mPresenter.loadComments(post)
        mPresenter.loadAutor(post.userId)
    }
}
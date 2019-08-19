package com.example.posts.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posts.R
import com.example.posts.models.Post

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.extras
        val post = data.getParcelable<Post>("post")

        setContentView(R.layout.activity_fragment)
        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, DetailFragment.getInstance(post)).commitNow()
    }
}
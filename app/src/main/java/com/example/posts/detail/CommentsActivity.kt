package com.example.posts.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posts.R
import com.example.posts.models.Post

class CommentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.extras
        val post = data?.getParcelable<Post>("post") ?: return

        setContentView(R.layout.activity_fragment)
        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, CommentsFragment.getInstance(post)).commitNow()
    }
}
package com.example.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posts.main.PostsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment)
        supportFragmentManager.beginTransaction().replace(
            R.id.contentFrame,
            PostsFragment.getInstance()
        ).commitNow()
    }
}
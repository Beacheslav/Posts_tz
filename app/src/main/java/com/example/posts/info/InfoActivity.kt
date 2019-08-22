package com.example.posts.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posts.R

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val userId = intent.extras?.getInt("user_id") ?: return

        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, InfoFragment.getInstance(userId)).commitNow()
    }
}
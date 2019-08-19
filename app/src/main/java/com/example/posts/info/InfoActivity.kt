package com.example.posts.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posts.R

class InfoActivity : AppCompatActivity() {

    lateinit var mView: InfoFragment
    lateinit var mPresenter: InfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val userId = intent.extras.getInt("user_id")

        mView = InfoFragment.getInstance(userId)
        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, mView).commitNow()
        mPresenter = InfoPresenter(mView)
    }
}
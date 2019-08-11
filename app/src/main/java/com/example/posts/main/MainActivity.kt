package com.example.posts.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posts.R

class MainActivity : AppCompatActivity() {

    lateinit var mView: MainFragment
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        mView = MainFragment.getInstance()
        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, mView).commitNow()
        mPresenter = MainPresenter(mView)
    }
}
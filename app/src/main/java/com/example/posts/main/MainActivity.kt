package com.example.posts.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posts.R

class MainActivity : AppCompatActivity() {
    private val mView: MainFragment = MainFragment.getInstance()
    val mPresenter: MainPresenter = MainPresenter(mView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, mView).commitNow()

        mPresenter.loadList()
        // назначит фрагмент презентеру, если нуль
    }
}
package com.example.posts.info

import android.os.Bundle
import com.example.posts.R
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class InfoActivity : MvpAppCompatActivity(), InfoViewActivity {

    @InjectPresenter
    lateinit var mPresenter: InfoPresenterActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }

    override fun showFragment() {
        val userId = intent.extras?.getInt("user_id") ?: return
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, InfoFragment.getInstance(userId))
            .commitNowAllowingStateLoss()
    }
}
package com.example.posts

import android.app.Application
import com.example.posts.dagger.DaggerPresenterComponent
import com.example.posts.dagger.PresenterComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        presenterComponent = DaggerPresenterComponent.create()
    }

    companion object{
        lateinit var presenterComponent : PresenterComponent
    }
}
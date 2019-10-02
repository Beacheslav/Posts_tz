package com.example.posts

import android.app.Application
import com.example.posts.dagger.ContextModule
import com.example.posts.dagger.DaggerPresenterComponent
import com.example.posts.dagger.PresenterComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        presenterComponent = DaggerPresenterComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object{
        lateinit var presenterComponent : PresenterComponent
    }
}
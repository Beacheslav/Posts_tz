package com.example.posts

import android.app.Application
import android.content.Context
import com.example.posts.dagger.ContextModule
import com.example.posts.dagger.DaggerPresenterComponent
import com.example.posts.dagger.PresenterComponent
import com.example.posts.dagger.PresenterModule
import javax.inject.Inject

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
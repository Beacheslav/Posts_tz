package com.example.posts.dagger

import android.content.Context
import com.example.posts.detail.DetailPresenter
import com.example.posts.info.InfoPresenter
import com.example.posts.main.PostsPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, PresenterModule::class, DbModule::class, ContextModule::class])
@Singleton
interface PresenterComponent {

    fun context(): Context
    fun injectApp(context: Context)
    fun injectPostRepo(postPresenter : PostsPresenter)
    fun injectInfoRepo(infoPresenter : InfoPresenter)
    fun injectCommentsRepo(detailPresenter : DetailPresenter)

}


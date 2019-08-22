package com.example.posts.dagger

import android.content.Context
import com.example.posts.detail.CommentsFragment
import com.example.posts.info.InfoFragment
import com.example.posts.main.PostsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, PresenterModule::class, DbModule::class])
@Singleton
interface PresenterComponent {

    fun context(): Context
    fun injectApp(context: Context)
    fun injectPostPresenter(fragment: PostsFragment)
    fun injectCommentsPresenter(fragment: CommentsFragment)
    fun injectInfoPresenter(fragment: InfoFragment)

}


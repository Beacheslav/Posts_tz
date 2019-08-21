package com.example.posts.dagger

import com.example.posts.detail.CommentsFragment
import com.example.posts.info.InfoFragment
import com.example.posts.main.PostsFragment
import dagger.Component
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton

@Component(modules = [ApiModule::class, PresenterModule::class])
@Singleton
interface PresenterComponent {

    fun injectPostPresenter(fragment: PostsFragment)
    fun injectCommentsPresenter(fragment: CommentsFragment)
    fun injectInfoPresenter(fragment: InfoFragment)

}


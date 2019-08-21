package com.example.posts.dagger

import com.example.posts.detail.CommentsFragment
import com.example.posts.info.InfoFragment
import com.example.posts.main.PostsFragment
import dagger.Component

@Component(modules = [PostPresenterModule::class, CommentsPresenterModule::class, InfoPresenterModule::class])
interface PresenterComponent {

    fun injectPostPresenter(fragment: PostsFragment)
    fun injectCommentsPresenter(fragment: CommentsFragment)
    fun injectInfoPresenter(fragment: InfoFragment)
}
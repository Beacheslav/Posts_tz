package com.example.posts.dagger

import com.example.posts.ApiSomaku
import com.example.posts.main.PostsPresenter
import com.example.posts.repo.PostRepoImplemented
import dagger.Module
import dagger.Provides

@Module
class PostPresenterModule {

    @Provides
    fun providesPostPresenter(): PostsPresenter {
        return PostsPresenter(PostRepoImplemented(ApiSomaku.create()))
    }
}
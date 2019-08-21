package com.example.posts.dagger

import com.example.posts.ApiSomaku
import com.example.posts.detail.CommentsPresenter
import com.example.posts.repo.CommentsRepoImplemented
import dagger.Module
import dagger.Provides

@Module
class CommentsPresenterModule {

    @Provides
    fun providesCommentsPresenter(): CommentsPresenter {
        return CommentsPresenter(CommentsRepoImplemented(ApiSomaku.create()))
    }
}
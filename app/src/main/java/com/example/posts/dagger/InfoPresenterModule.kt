package com.example.posts.dagger

import com.example.posts.ApiSomaku
import com.example.posts.info.InfoPresenter
import com.example.posts.repo.InfoRepoImplemented
import dagger.Module
import dagger.Provides

@Module
class InfoPresenterModule {

    @Provides
    fun providesInfoPresenter(): InfoPresenter {
        return InfoPresenter(InfoRepoImplemented(ApiSomaku.create()))
    }
}
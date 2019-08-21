package com.example.posts.dagger

import com.example.posts.ApiSomaku
import com.example.posts.detail.CommentsPresenter
import com.example.posts.info.InfoPresenter
import com.example.posts.main.PostsPresenter
import com.example.posts.repo.*
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class PresenterModule {

    @Provides
    fun providesPostPresenter(postRepo: PostRepo): PostsPresenter {
        return PostsPresenter(postRepo)
    }

    @Provides
    fun providesPostRepo(api : ApiSomaku): PostRepo {
        return PostRepoImplemented(api)
    }

    @Provides
    fun providesCommentPresenter(commentsRepo: CommentsRepo): CommentsPresenter {
        return CommentsPresenter(commentsRepo)
    }

    @Provides
    fun providesCommentRepo(api : ApiSomaku): CommentsRepo {
        return CommentsRepoImplemented(api)
    }

    @Provides
    fun providesInfoPresenter(infoRepo: InfoRepo): InfoPresenter {
        return InfoPresenter(infoRepo)
    }

    @Provides
    fun providesInfoRepo(api : ApiSomaku): InfoRepo {
        return InfoRepoImplemented(api)
    }
}
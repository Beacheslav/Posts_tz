package com.example.posts.dagger

import com.example.posts.ApiSomaku
import com.example.posts.db.*
import com.example.posts.detail.CommentsPresenter
import com.example.posts.info.InfoPresenter
import com.example.posts.main.PostsPresenter
import com.example.posts.repo.*
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun providesPostPresenter(postRepo: PostRepo): PostsPresenter {
        return PostsPresenter(postRepo)
    }

    @Provides
    fun providesPostRepo(api : ApiSomaku, postDao: PostDao): PostRepo {
        return PostRepoImplemented(api, postDao)
    }

    @Provides
    fun providesCommentPresenter(commentsRepo: CommentsRepo): CommentsPresenter {
        return CommentsPresenter(commentsRepo)
    }

    @Provides
    fun providesCommentRepo(api : ApiSomaku, autorDao : AutorDao, commentDao : CommentDao): CommentsRepo {
        return CommentsRepoImplemented(api, autorDao, commentDao)
    }

    @Provides
    fun providesInfoPresenter(infoRepo: InfoRepo): InfoPresenter {
        return InfoPresenter(infoRepo)
    }

    @Provides
    fun providesInfoRepo(api : ApiSomaku, albumDao: AlbumDao, photoDao: PhotoDao, autorDao: AutorDao): InfoRepo {
        return InfoRepoImplemented(api, albumDao, photoDao, autorDao)
    }
}
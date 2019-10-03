package com.example.posts.dagger

import android.content.Context
import com.example.posts.MainActivity
import com.example.posts.albums.AlbumController
import com.example.posts.detail.CommentsPresenter
import com.example.posts.albums.AlbumPresenter
import com.example.posts.detail.CommentsController
import com.example.posts.posts.PostsController
import com.example.posts.posts.PostsPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, PresenterModule::class, DbModule::class, ContextModule::class])
@Singleton
interface PresenterComponent {

    fun context(): Context
    fun injectApp(context: Context)
    fun injectPostPresenter(postPresenter : PostsPresenter)
    fun injectInfoPresenter(albumPresenter : AlbumPresenter)
    fun injectCommentsPresenter(detailPresenter : CommentsPresenter)
    fun inject(postsController: PostsController)
    fun inject(commentsController: CommentsController)
    fun inject(albumController: AlbumController)
}


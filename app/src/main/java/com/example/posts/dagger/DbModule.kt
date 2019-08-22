package com.example.posts.dagger

import android.content.Context
import androidx.room.Room
import com.example.posts.db.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class DbModule {

    @Provides
    @Singleton
    fun provideDb(appContext : Context) : Db {
        val db = Room.databaseBuilder(
            appContext,
            Db::class.java, "app.db"
        ).build()
        return db
    }

    @Provides
    @Singleton
    fun provideAlbumDao(db : Db) : AlbumDao {
        return db.AlbumDao()
    }

    @Provides
    @Singleton
    fun provideAutorDao(db : Db) : AutorDao {
        return db.AutorDao()
    }

    @Provides
    @Singleton
    fun provideCommentDao(db: Db) : CommentDao {
        return db.CommentDao()
    }

    @Provides
    @Singleton
    fun providePhotoDAo(db : Db) : PhotoDao{
        return db.PhotoDao()
    }

    @Provides
    @Singleton
    fun providePostDao(db: Db) : PostDao {
        return db.PostDao()
    }
}
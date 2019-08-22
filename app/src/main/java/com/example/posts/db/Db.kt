package com.example.posts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.posts.models.*

@Database(
    entities = [Album::class, Autor::class, Comment::class, Photo::class, Post::class],
    version = 1,
    exportSchema = false
)

abstract class Db : RoomDatabase(){
    abstract fun AlbumDao(): AlbumDao
    abstract fun AutorDao() : AutorDao
    abstract fun CommentDao() : CommentDao
    abstract fun PhotoDao() : PhotoDao
    abstract fun PostDao() : PostDao

    companion object {
        @Volatile private var instance: Db? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            Db::class.java, "posts.db")
            .build()
    }
}
package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.posts.models.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAll(): List<Post>

    @Query("SELECT * FROM posts WHERE id = :id")
    fun equalsId(id: Int): List<Post>

    @Insert
    fun insert(post: Post)

}
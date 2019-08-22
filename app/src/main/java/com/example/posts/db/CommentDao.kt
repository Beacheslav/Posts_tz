package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.posts.models.Comment

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments")
    fun getAll(): List<Comment>

    @Query("SELECT * FROM comments WHERE id = :id")
    fun equalsId(id: Int): List<Comment>

    @Insert
    fun insert(comment: Comment)

}
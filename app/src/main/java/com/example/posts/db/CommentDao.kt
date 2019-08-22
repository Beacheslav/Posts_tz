package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.posts.models.Comment
import io.reactivex.Single

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments")
    fun getAll(): Single<List<Comment>>

    @Query("SELECT * FROM comments WHERE id = :id")
    fun equalsId(id: Int): Single<List<Comment>>

    @Insert
    fun insert(comment: Comment)

    @Insert
    fun insertAll(comments : List<Comment>)
}
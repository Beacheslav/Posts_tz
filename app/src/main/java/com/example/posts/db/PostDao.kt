package com.example.posts.db

import androidx.room.*
import com.example.posts.models.Post
import io.reactivex.Single

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAll(): Single<List<Post>>

    @Query("SELECT * FROM posts WHERE id = :id")
    fun equalsId(id: Int): Single<List<Post>>

    @Insert
    fun insert(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts : List<Post>)
}
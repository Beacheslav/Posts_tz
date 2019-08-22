package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.posts.models.Photo
import io.reactivex.Single

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    fun getAll(): Single<List<Photo>>

    @Query("SELECT * FROM photos WHERE id = :id")
    fun equalsId(id: Int): Single<List<Photo>>

    @Insert
    fun insert(photo: Photo)

    @Insert
    fun insertAll(photos : List<Photo>)
}
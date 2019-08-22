package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.posts.models.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    fun getAll(): List<Photo>

    @Query("SELECT * FROM photos WHERE id = :id")
    fun equalsId(id: Int): List<Photo>

    @Insert
    fun insert(photo: Photo)

}
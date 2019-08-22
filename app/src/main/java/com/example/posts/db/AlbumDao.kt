package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.posts.models.Album

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums")
    fun getAll(): List<Album>

    @Query("SELECT * FROM albums WHERE id = :id")
    fun equalsId(id: Int): List<Album>

    @Insert
    fun insert(album : Album)

}
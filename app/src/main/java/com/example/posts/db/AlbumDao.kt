package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.posts.models.Album
import io.reactivex.Single

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums")
    fun getAll(): Single<List<Album>>

    @Query("SELECT * FROM albums WHERE id = :id")
    fun equalsId(id: Int): Single<List<Album>>

    @Insert
    fun insert(album : Album)

    @Insert
    fun insertAll(albums : List<Album>)

}
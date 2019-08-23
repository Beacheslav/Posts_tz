package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.posts.models.Photo
import io.reactivex.Single

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    fun getAll(): Single<List<Photo>>

    @Query("SELECT * FROM photos WHERE albumId = :albumId")
    fun equalsId(albumId: Int): Single<List<Photo>>

    @Insert
    fun insert(photo: Photo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photos : List<Photo>)
}
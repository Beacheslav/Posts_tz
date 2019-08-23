package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.posts.models.Autor
import io.reactivex.Single

@Dao
interface AutorDao {

    @Query("SELECT * FROM autors")
    fun getAll(): Single<List<Autor>>

    @Query("SELECT * FROM autors WHERE id = :id")
    fun equalsId(id: Int): Single<List<Autor>>

    @Insert
    fun insert(autor : Autor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(autors : List<Autor>)
}
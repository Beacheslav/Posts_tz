package com.example.posts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.posts.models.Autor

@Dao
interface AutorDao {

    @Query("SELECT * FROM autors")
    fun getAll(): List<Autor>

    @Query("SELECT * FROM autors WHERE id = :id")
    fun equalsId(id: Int): List<Autor>

    @Insert
    fun insert(autor : Autor)

}
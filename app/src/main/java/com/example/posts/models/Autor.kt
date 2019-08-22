package com.example.posts.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "autors")
data class Autor(@PrimaryKey val id : Int,
                 @ColumnInfo(name = "name") val name : String,
                 @ColumnInfo(name = "username")val username : String,
                 @ColumnInfo(name = "email")val email : String)
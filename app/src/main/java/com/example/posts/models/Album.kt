package com.example.posts.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(@PrimaryKey val id : Int,
                 @ColumnInfo(name = "title") val title : String)
package com.example.posts.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(
    @ColumnInfo(name = "userId") val userId: Int,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String
)
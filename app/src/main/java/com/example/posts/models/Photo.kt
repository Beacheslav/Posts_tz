package com.example.posts.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo(@PrimaryKey val id : Int,
                 @ColumnInfo(name = "albumId") val albumId : Int)
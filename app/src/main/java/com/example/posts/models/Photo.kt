package com.example.posts.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo(@PrimaryKey val id : Int)
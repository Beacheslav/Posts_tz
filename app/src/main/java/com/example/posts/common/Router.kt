package com.example.posts.common

import com.example.posts.models.Post

interface Router {
    fun showComments(item: Post)
    fun showAlbums(userId: Int)
}
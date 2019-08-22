package com.example.posts.repo

import com.example.posts.models.Post
import io.reactivex.functions.Consumer

interface PostRepo {
    fun getPosts(consumer: Consumer<List<Post>>, errorConsumer: Consumer<Throwable>)
    fun getPostsOfDb(consumer: Consumer<List<Post>>, errorConsumer: Consumer<Throwable>)
}
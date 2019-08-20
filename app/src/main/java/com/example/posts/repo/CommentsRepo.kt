package com.example.posts.repo

import com.example.posts.models.Autor
import com.example.posts.models.Comment
import io.reactivex.functions.Consumer

interface CommentsRepo {

    fun loadAutor(id : Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>)
    fun loadComments(id : Int, consumer: Consumer<List<Comment>>, errorConsumer: Consumer<Throwable>)
}
package com.example.posts.repo

import com.example.posts.models.Autor
import com.example.posts.models.Comment
import io.reactivex.functions.Consumer

interface CommentsRepo {

    fun getAutorOfApi(id : Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>)
    fun getCommentsOfApi(id : Int, consumer: Consumer<List<Comment>>, errorConsumer: Consumer<Throwable>)
    fun getAutorOfBd(id: Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>)
    fun getCommentsOfBd(id: Int, consumer: Consumer<List<Comment>>, errorConsumer: Consumer<Throwable>)
    fun destroy()
}
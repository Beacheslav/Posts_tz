package com.example.posts.repo

import com.example.posts.ApiSomaku
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class CommentsRepoImplemented(val api : ApiSomaku) : CommentsRepo {

    override fun loadAutor(id : Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>) {
        api.getAutor(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun loadComments(id : Int, consumer: Consumer<List<Comment>>, errorConsumer: Consumer<Throwable>) {
        api.getComments(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

}
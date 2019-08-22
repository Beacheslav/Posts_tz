package com.example.posts.repo

import com.example.posts.ApiSomaku
import com.example.posts.db.AutorDao
import com.example.posts.db.CommentDao
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class CommentsRepoImplemented(val api : ApiSomaku, val autorDao: AutorDao, val commentDao: CommentDao) : CommentsRepo {

    //API
    override fun getAutorOfApi(id : Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>) {
        api.getAutor(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getCommentsOfApi(id : Int, consumer: Consumer<List<Comment>>, errorConsumer: Consumer<Throwable>) {
        api.getComments(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    //BD
    override fun getAutorOfBd(id : Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>) {
        autorDao.equalsId(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getCommentsOfBd(id : Int, consumer: Consumer<List<Comment>>, errorConsumer: Consumer<Throwable>) {
        commentDao.equalsId(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }
}
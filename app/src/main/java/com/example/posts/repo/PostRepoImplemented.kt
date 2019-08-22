package com.example.posts.repo

import com.example.posts.ApiSomaku
import com.example.posts.db.Db
import com.example.posts.db.PostDao
import com.example.posts.models.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class PostRepoImplemented(val api : ApiSomaku, val dao : PostDao) : PostRepo {

    override fun getPostsOfApi(consumer: Consumer<List<Post>>, errorConsumer: Consumer<Throwable>) {
        api.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getPostsOfDb(consumer: Consumer<List<Post>>, errorConsumer: Consumer<Throwable>) {
        dao.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }
}
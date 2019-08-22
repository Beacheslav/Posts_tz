package com.example.posts.repo

import android.annotation.SuppressLint
import android.util.Log
import com.example.posts.ApiSomaku
import com.example.posts.db.Db
import com.example.posts.db.PostDao
import com.example.posts.models.Post
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class PostRepoImplemented(val api : ApiSomaku, val dao : PostDao) : PostRepo {

    @SuppressLint("CheckResult")
    override fun getPosts(consumer: Consumer<List<Post>>, errorConsumer: Consumer<Throwable>) {
        Single.zip(
            dao.getAll(),
            api.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    errorConsumer.accept(it)
                    ArrayList()
                },
            BiFunction<List<Post>, List<Post>, List<Post>> { fromDao, fromApi ->
                if(fromApi.isNotEmpty()){
                    dao.insertAll(fromApi)
                }
                val list = emptyList<Post>().toMutableList().apply {
                    addAll(fromDao)
                    addAll(fromApi)
                }
                val clearList = list.distinctBy {
                    it.id
                }
                clearList
            }
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }

    override fun getPostsOfDb(consumer: Consumer<List<Post>>, errorConsumer: Consumer<Throwable>) {
        dao.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
//        val bc = BiConsumer<Consumer<List<Post>>, Consumer<Throwable>>{consumer, errorConsumer ->
//
//        }
    }
}
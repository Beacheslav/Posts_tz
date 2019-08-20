package com.example.posts.repo

import com.example.posts.ApiSomaku
import com.example.posts.models.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class PostRepoImplemented(val api : ApiSomaku) : PostRepo {
    override fun getPosts(consumer: Consumer<List<Post>>, errorConsumer: Consumer<Throwable>) {

        api.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(consumer, errorConsumer)
    }
}
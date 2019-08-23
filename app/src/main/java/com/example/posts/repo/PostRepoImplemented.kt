package com.example.posts.repo

import com.example.posts.ApiSomaku
import com.example.posts.db.PostDao
import com.example.posts.models.Post
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class PostRepoImplemented(val api: ApiSomaku, val dao: PostDao) : PostRepo {

    val disposable = CompositeDisposable()

    override fun getPosts(consumer: Consumer<List<Post>>, errorConsumer: Consumer<Throwable>) {
        disposable.add(Single.zip(
            dao.getAll()
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io()),
            api.getPosts()
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    dao.getAll()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(consumer, errorConsumer)
                    errorConsumer.accept(it)
                    ArrayList()

                },
            BiFunction<List<Post>, List<Post>, List<Post>> { fromDao, fromApi ->
                if (fromApi.isNotEmpty()) {
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
            .subscribe(consumer, errorConsumer))
    }

    override fun destroy(){
        disposable.dispose()
        disposable.clear()
    }
}
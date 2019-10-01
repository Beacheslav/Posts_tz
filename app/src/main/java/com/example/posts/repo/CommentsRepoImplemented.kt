package com.example.posts.repo

import com.example.posts.ApiSomaku
import com.example.posts.db.AutorDao
import com.example.posts.db.CommentDao
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class CommentsRepoImplemented(val api : ApiSomaku, val autorDao: AutorDao, val commentDao: CommentDao) : CommentsRepo {

    var disposable = CompositeDisposable()

    override fun create() {
        disposable = CompositeDisposable()
    }

    override fun getAutor(id : Int, consumer: Consumer<List<Autor>>, errorConsumer: Consumer<Throwable>) {

        disposable.add(Single.zip(
            autorDao.equalsId(id)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io()),
            api.getAutor(id)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    autorDao.equalsId(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(consumer, errorConsumer)
                    errorConsumer.accept(it)
                    ArrayList()

                },
            BiFunction<List<Autor>, List<Autor>, List<Autor>> { fromDao, fromApi ->
                if (fromApi.isNotEmpty()) {
                    autorDao.insertAll(fromApi)
                }
                val list = emptyList<Autor>().toMutableList().apply {
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

    override fun getComments(id : Int, consumer: Consumer<List<Comment>>, errorConsumer: Consumer<Throwable>) {

        disposable.add(Single.zip(
            commentDao.equalsId(id)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io()),
            api.getComments(id)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    commentDao.equalsId(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(consumer, errorConsumer)
                    errorConsumer.accept(it)
                    ArrayList()

                },
            BiFunction<List<Comment>, List<Comment>, List<Comment>> { fromDao, fromApi ->
                if (fromApi.isNotEmpty()) {
                    commentDao.insertAll(fromApi)
                }
                val list = emptyList<Comment>().toMutableList().apply {
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

    override fun  destroy(){
        disposable.dispose()
        disposable.clear()
    }
}
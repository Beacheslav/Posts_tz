package com.example.posts.info

import com.example.posts.ApiSomaku
import com.example.posts.InfoAdapter
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import com.example.posts.repo.InfoRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class InfoPresenter(val infoRepo: InfoRepo) : InfoContract.Presenter {

    public var mView: InfoContract.View? = null
    private var mAutor: Autor? = null

    override fun loadAutor(userId: Int?) {
        if (userId == null) return
        infoRepo.getAutor(userId, Consumer {

            val resp = it as ArrayList<Autor>
            if (resp.isNotEmpty()) {
                mAutor = resp[0]
            }
            mView?.showAutor(mAutor)
            loadListAlbum(mAutor?.id)

        }, Consumer {
            mView?.showLoadError()
            it.printStackTrace()
        })
    }

    override fun loadListAlbum(userId: Int?) {
        if (userId == null) return

        infoRepo.getAlbums(userId, Consumer{
            Observable.fromIterable(it)
                .flatMap {  albumSource ->
                    Observable.zip(
                        Observable.just(albumSource),
                        photosToObservable(albumSource.id),
                        BiFunction<Album, List<Photo>, InfoAdapter.Info> { album, photos ->
                            InfoAdapter.Info(album.title, "${photos.size} Photos")
                        }
                    )
                }
                .toList()
                .subscribe { result ->
                    mView?.showList(result)
                }
        }, Consumer {
            mView?.showLoadError()
            it.printStackTrace()
        })
    }

    fun photosToObservable(albumId: Int): Observable<List<Photo>> =
        Observable.create { emiter ->
            infoRepo.getPhotos(albumId, Consumer {
                emiter.onNext(it)
                emiter.onComplete()
            }, Consumer {
                emiter.onError(it)
            })
        }

    override fun start() {
        return
    }
}
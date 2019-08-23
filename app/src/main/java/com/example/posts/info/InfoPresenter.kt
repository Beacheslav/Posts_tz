package com.example.posts.info

import android.util.Log
import com.example.posts.InfoAdapter
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import com.example.posts.repo.InfoRepo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import javax.inject.Inject

class InfoPresenter @Inject constructor (val infoRepo: InfoRepo) : InfoContract.Presenter {

    public var mView: InfoContract.View? = null
    private var mAutor: Autor? = null

    override fun loadAutor(userId: Int?) {
        if (userId == null) return
        infoRepo.getAutorOfApi(userId, Consumer {

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

        infoRepo.getAlbumsOfApi(userId, Consumer{
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
            infoRepo.getPhotosOfApi(albumId, Consumer {
                emiter.onNext(it)
                emiter.onComplete()
            }, Consumer {
                it.printStackTrace()
                emiter.onError(it)
            })
        }
}
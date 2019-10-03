package com.example.posts.albums

import com.example.posts.App
import com.example.posts.InfoAdapter
import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Photo
import com.example.posts.repo.InfoRepo
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AlbumPresenter @Inject constructor(var infoRepo: InfoRepo) : MvpPresenter<AlbumView>(){

    private var mAutor: Autor? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.presenterComponent.injectInfoPresenter(this)
        viewState.loadScreen()
    }

    fun loadAutor(userId: Int?) {
        if (userId == null) return
        infoRepo.getAutorOfApi(userId, Consumer {

            val resp = it as ArrayList<Autor>
            if (resp.isNotEmpty()) {
                mAutor = resp[0]
            }
            viewState.showAutor(mAutor)
            loadListAlbum(mAutor?.id)

        }, Consumer {
            viewState.showLoadError()
            it.printStackTrace()
        })
    }

    fun loadListAlbum(userId: Int?) {
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
                    viewState.showList(result)
                }
        }, Consumer {
            viewState.showLoadError()
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
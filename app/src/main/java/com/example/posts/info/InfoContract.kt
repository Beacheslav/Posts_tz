package com.example.posts.info

import com.example.posts.base.BasePresenter
import com.example.posts.base.BaseView
import com.example.posts.models.Album
import com.example.posts.models.Autor

interface InfoContract {

    interface Presenter : BasePresenter {

        fun loadListAlbum(id : Int?)

        fun loadAutor(id : Int?)

        fun loadPhotos(id : Int)

    }

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun updateUi()

        fun updatePhotoCount(count: Int, id : Int)

        fun showLoadError()

        fun updateListUi(list : ArrayList<Album>?, autor : Autor?)

    }
}
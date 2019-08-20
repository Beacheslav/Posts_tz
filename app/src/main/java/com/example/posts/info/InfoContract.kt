package com.example.posts.info

import com.example.posts.InfoAdapter
import com.example.posts.base.BasePresenter
import com.example.posts.base.BaseView
import com.example.posts.models.Album
import com.example.posts.models.Autor

interface InfoContract {

    interface Presenter : BasePresenter {

        fun loadListAlbum(id : Int?)

        fun loadAutor(id : Int?)

        fun loadPhotos()

    }

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun updateUi()

        fun updatePhotoCount(count: Int, id : Int)

        fun showLoadError()

        fun showList(list : List<InfoAdapter.Info>?)
        fun showAutor(mAutor: Autor?)

    }
}
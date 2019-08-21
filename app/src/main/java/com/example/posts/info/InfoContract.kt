package com.example.posts.info

import com.example.posts.InfoAdapter
import com.example.posts.models.Autor

interface InfoContract {

    interface Presenter {

        fun loadListAlbum(id : Int?)
        fun loadAutor(id : Int?)
    }

    interface View {

        fun updatePhotoCount(count: Int, id : Int)
        fun showLoadError()
        fun showList(list : List<InfoAdapter.Info>?)
        fun showAutor(mAutor: Autor?)

    }
}
package com.example.posts.detail

import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post

interface CommentsContract {

    interface Presenter {

        fun loadComments(post: Post)
        fun loadAutor(id : Int)
        fun itemClick(position: Int)

    }

    interface View {

        fun showAlbums(userId : Int)
        fun updateListUi(listType : ArrayList<Comment>?, autor: Autor?)
        fun showLoadError()

    }
}
package com.example.posts.main

import com.example.posts.models.Post

interface PostsContract {
    interface Presenter {

        fun loadList()
        fun itemClick(position: Int)

    }

    interface View {

        fun updateListUi(list: ArrayList<Post>)
        fun showLoadError()
        fun showPost(item: Post)

    }
}
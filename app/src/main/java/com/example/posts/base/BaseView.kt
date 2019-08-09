package com.example.posts.base

interface BaseView<T> {

    fun setPresenter(presenter: T)

    fun isActive() : Boolean
}
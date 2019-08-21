package com.example.posts

import com.example.posts.models.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSomaku {

    @GET("/posts")
    fun getPosts(): Single<ArrayList<Post>>

    @GET("/comments")
    fun getComments(@Query("postId") id: Int): Single<ArrayList<Comment>>

    @GET("/users")
    fun getAutor(@Query("id") id: Int): Single<ArrayList<Autor>>

    @GET("/albums")
    fun getAlbums(@Query("userId") id: Int): Single<ArrayList<Album>>

    @GET("/photos")
    fun getPhotos(@Query("albumId") id: Int): Single<ArrayList<Photo>>
}
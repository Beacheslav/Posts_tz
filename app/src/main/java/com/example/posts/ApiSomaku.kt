package com.example.posts

import com.example.posts.models.Album
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSomaku {

    @GET("/posts")
    fun getPosts(): Call<ArrayList<Post>>

    @GET("/comments")
    fun getComments(@Query("postId") id : Int): Call<ArrayList<Comment>>

    @GET("/users")
    fun getAutor(@Query("id") id : Int): Call<ArrayList<Autor>>

    @GET("/albums")
    fun getAlbums(@Query("userId") id : Int): Call<ArrayList<Album>>

    companion object Factory {
        fun create(): ApiSomaku {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.somaku.com")
                .build()

            return retrofit.create(ApiSomaku::class.java)
        }
    }
}
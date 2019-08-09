package com.example.posts

import com.example.posts.models.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiSomaku {

    @GET("/posts")
    fun getPosts(): Call<ArrayList<Post>>

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
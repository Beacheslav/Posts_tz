package com.example.posts

import com.example.posts.models.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor



interface ApiSomaku {

    @GET("/posts")
    fun getPosts(): Single<ArrayList<Post>>

    @GET("/comments")
    fun getComments(@Query("postId") id : Int): Single<ArrayList<Comment>>

    @GET("/users")
    fun getAutor(@Query("id") id : Int): Single<ArrayList<Autor>>

    @GET("/albums")
    fun getAlbums(@Query("userId") id : Int): Single<ArrayList<Album>>

    @GET("/photos")
    fun getPhotos(@Query("albumId") id : Int): Single<ArrayList<Photo>>



    companion object Factory {
        fun create(): ApiSomaku {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.somaku.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiSomaku::class.java)
        }
    }
}
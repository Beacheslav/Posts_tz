package com.example.posts.dagger

import android.util.Log
import com.example.posts.ApiSomaku
import com.github.ajalt.timberkt.Timber
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(client : OkHttpClient) : ApiSomaku{

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiSomaku::class.java)
    }

    @Provides
    @Singleton
    fun provideClient() :OkHttpClient {
        val loger = object :  HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.d("OkHttp", message)
            }
        }
        val interceptor = HttpLoggingInterceptor(loger)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return client
    }
}
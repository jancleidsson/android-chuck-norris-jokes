package com.jss.chucknorrisjokes.framework.api

import com.jss.chucknorrisjokes.core.entity.Joke
import com.jss.chucknorrisjokes.util.Constants
import io.reactivex.rxjava3.core.Observable
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisIOService {

    @GET("jokes/random?")
    fun randomJoke(
            @Query("category") category: String,
    ): Observable<Joke>

    @GET("jokes/categories")
    fun getCategories(): Observable<ArrayList<String>>

    companion object {
        fun create(): ChuckNorrisIOService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(Constants.CHUCK_NORRIS_IO_BASE_URL.toHttpUrlOrNull()!!)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                    .create(ChuckNorrisIOService::class.java)
        }
    }
}
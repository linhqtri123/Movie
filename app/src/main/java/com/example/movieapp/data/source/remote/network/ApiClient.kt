package com.example.movieapp.data.source.remote.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Create by Linh Le H. M. on 11/25/20
 */
object ApiClient {
    private const val baseURL = "https://api.themoviedb.org/3/"
    private var retrofit: Retrofit? = null
    private const val REQUEST_TIMEOUT = 60

    internal fun createService(): ApiService? = getClient()?.create(ApiService::class.java)

    private fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(
                    OkHttpClient().newBuilder()
                        .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .build()
                )
                .build()
        }
        return retrofit
    }
}

package com.example.movieapp.data.source.remote.network

import com.example.movieapp.data.model.MoviePages
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * Create by Linh Le H. M. on 11/25/20
 */
interface ApiService {
    @GET("movie/now_playing?api_key=e0006e65d0ed3abc9b55fb77dece9d2e")
    fun getListNowPlaying(): Single<Response<MoviePages>>

    @GET("movie/popular?api_key=e0006e65d0ed3abc9b55fb77dece9d2e")
    fun getListPopular(): Single<Response<MoviePages>>
}

package com.example.movieapp.data.source.remote

import com.example.movieapp.data.model.MoviePages
import com.example.movieapp.data.source.datasource.HomeDataSource
import com.example.movieapp.data.source.remote.network.ApiClient
import com.example.movieapp.data.source.remote.network.ApiService
import io.reactivex.Single
import retrofit2.Response

/**
 * Create by Linh Le H. M. on 11/25/20
 */
class HomeRemoteDataSource(private val api: ApiService? = ApiClient.createService()) :
    HomeDataSource {
    override fun getListNowPlaying(): Single<Response<MoviePages>>? = api?.getListNowPlaying()
}

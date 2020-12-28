package com.example.movieapp.data.source

import com.example.movieapp.data.model.MoviePages
import com.example.movieapp.data.source.datasource.HomeDataSource
import com.example.movieapp.data.source.remote.HomeRemoteDataSource
import com.example.movieapp.data.source.remote.network.ApiClient
import io.reactivex.Single
import retrofit2.Response

/**
 * Create by Linh Le H. M. on 11/25/20
 */
class HomeRepository : HomeDataSource {
    private val homeRemoteDataSource = HomeRemoteDataSource(ApiClient.createService())
    override fun getListNowPlaying(): Single<Response<MoviePages>>? =
        homeRemoteDataSource.getListNowPlaying()

    override fun getListPopular(): Single<Response<MoviePages>>? =
        homeRemoteDataSource.getListPopular()

    override fun getListTopRated(): Single<Response<MoviePages>>? =
        homeRemoteDataSource.getListTopRated()

    override fun getListUpcoming(): Single<Response<MoviePages>>? =
        homeRemoteDataSource.getListUpcoming()
}

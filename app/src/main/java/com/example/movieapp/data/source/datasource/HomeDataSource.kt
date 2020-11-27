package com.example.movieapp.data.source.datasource

import com.example.movieapp.data.model.MoviePages
import io.reactivex.Single
import retrofit2.Response

/**
 * Create by Linh Le H. M. on 11/25/20
 */
interface HomeDataSource {
    fun getListNowPlaying(): Single<Response<MoviePages>>?
}

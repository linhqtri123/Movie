package com.example.movieapp.ui.home

import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.model.MoviePages
import io.reactivex.Single
import retrofit2.Response

/**
 * Create by Linh Le H. M. on 12/7/20
 */
interface HomeMVContract {
    fun getListNowPlayingFromServer(): Single<Response<MoviePages>>?

    fun getListPopularFromServer(): Single<Response<MoviePages>>?

    fun getListTopRatedFromServer(): Single<Response<MoviePages>>?

    fun getListUpcomingFromServer(): Single<Response<MoviePages>>?

    fun getListMovie(): MutableList<MovieModel>

    fun getPopularList(): MutableList<MovieModel>

    fun getTopRatedList(): MutableList<MovieModel>

    fun getUpcomingList(): MutableList<MovieModel>
}

package com.example.movieapp.ui.home

import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.model.MoviePages
import com.example.movieapp.data.source.HomeRepository
import io.reactivex.Single
import retrofit2.Response

/**
 * Create by Linh Le H. M. on 12/7/20
 */
class HomeViewModel(private val repository: HomeRepository) : HomeMVContract {
    private var movieList = mutableListOf<MovieModel>()
    private var popularList = mutableListOf<MovieModel>()
    private var topRatedList = mutableListOf<MovieModel>()
    private var upcomingList = mutableListOf<MovieModel>()

    override fun getListNowPlayingFromServer(): Single<Response<MoviePages>>? =
        repository.getListNowPlaying()?.doOnSuccess {
            if (it.isSuccessful) {
                it.body()?.results?.let { list ->
                    movieList.addAll(list)
                }
            }
        }

    override fun getListPopularFromServer(): Single<Response<MoviePages>>? =
        repository.getListPopular()?.doOnSuccess {
            if (it.isSuccessful) {
                it.body()?.results?.let { list ->
                    popularList.addAll(list)
                }
            }
        }

    override fun getListTopRatedFromServer(): Single<Response<MoviePages>>? =
        repository.getListTopRated()?.doOnSuccess {
            if (it.isSuccessful) {
                it.body()?.results?.let { list ->
                    topRatedList.addAll(list)
                }
            }
        }

    override fun getListUpcomingFromServer(): Single<Response<MoviePages>>? =
        repository.getListUpcoming()?.doOnSuccess {
            if (it.isSuccessful) {
                it.body()?.results?.let { list ->
                    upcomingList.addAll(list)
                }
            }
        }

    override fun getListMovie(): MutableList<MovieModel> = movieList

    override fun getPopularList(): MutableList<MovieModel> = popularList

    override fun getTopRatedList(): MutableList<MovieModel> = topRatedList

    override fun getUpcomingList(): MutableList<MovieModel> = upcomingList
}

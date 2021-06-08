package com.example.movieapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.data.source.HomeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Create by Linh Le H. M. on 12/7/20
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private lateinit var adapterPopular: MovieAdapter
    private lateinit var adapterTopRated: MovieAdapter
    private lateinit var adapterUpcoming: MovieAdapter
    private var viewModel: HomeMVContract? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = HomeViewModel(HomeRepository())
        initData()
        initAdapter()
    }

    private fun initAdapter() {
        adapter = MovieAdapter(viewModel?.getListMovie() ?: mutableListOf())
        adapterPopular = MovieAdapter(viewModel?.getPopularList() ?: mutableListOf())
        adapterTopRated = MovieAdapter(viewModel?.getTopRatedList() ?: mutableListOf())
        adapterUpcoming = MovieAdapter(viewModel?.getUpcomingList() ?: mutableListOf())
        recyclerViewNowPlayingContainer?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNowPlayingContainer?.adapter = adapter
        recyclerViewPopularContainer?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularContainer?.adapter = adapterPopular
        recyclerViewTopRatedContainer?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTopRatedContainer?.adapter = adapterTopRated
        recyclerViewUpcomingContainer?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewUpcomingContainer?.adapter = adapterUpcoming
    }

    @SuppressLint("CheckResult")
    private fun initData() {
        viewModel?.getListNowPlayingFromServer()
            ?.subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                adapter.notifyDataSetChanged()
            }, {
                //No-op
            })
        viewModel?.getListPopularFromServer()
            ?.subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                adapterPopular.notifyDataSetChanged()
            }, {
                //No-op
            })
        viewModel?.getListTopRatedFromServer()
            ?.subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                adapterTopRated.notifyDataSetChanged()
            }, {
                //No-op
            })
        viewModel?.getListUpcomingFromServer()
            ?.subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                adapterUpcoming.notifyDataSetChanged()
            }, {
                //No-op
            })
    }
}

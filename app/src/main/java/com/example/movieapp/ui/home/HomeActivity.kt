package com.example.movieapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
        recyclerViewNowPlayingContainer?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNowPlayingContainer?.adapter = adapter
        recyclerViewPopularContainer?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularContainer?.adapter = adapterPopular
    }

    @SuppressLint("CheckResult")
    private fun initData() {
        viewModel?.getListNowPlayingFromServer()
            ?.subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                adapter.notifyDataSetChanged()
            }, {
                it.message?.let { it1 -> Log.d("Throwable", it1) }
            })
        viewModel?.getListPopularFromServer()
            ?.subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSuccess {
                Log.e("xxx", "doOnSuccess" + Thread.currentThread().toString())
            }
            ?.subscribe({
                Log.e("xxx", "subscribe" + Thread.currentThread().toString())
                adapterPopular.notifyDataSetChanged()
            }, {})
    }
}

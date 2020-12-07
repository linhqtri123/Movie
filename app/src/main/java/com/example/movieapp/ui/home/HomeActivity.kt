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
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Create by Linh Le H. M. on 12/7/20
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private var viewModel: HomeMVContract? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = HomeViewModel(HomeRepository())
        initData()
        initAdapter()
    }

    private fun initAdapter() {
        adapter = MovieAdapter(viewModel?.getListNowPlaying() ?: mutableListOf())
        recyclerViewContainer?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewContainer?.adapter = adapter
    }

    @SuppressLint("CheckResult")
    private fun initData() {
        viewModel?.getListNowPlayingFromServer()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                adapter.notifyDataSetChanged()
            }, {
                it.message?.let { it1 -> Log.d("Throwable", it1) }
            })
    }
}

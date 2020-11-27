package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.data.source.HomeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var repository: HomeRepository? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository = HomeRepository()
        getData()
    }

    @SuppressLint("CheckResult")
    private fun getData() {
        repository?.getListNowPlaying()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
                if (it.isSuccessful) {
                    it.body()?.results?.forEach { movie ->
                        Log.d("VaiLoL", "getData: ${movie.title}")
                    }
                }
            }, {
                Log.d("Throwable", "getData: ${it.message}")
            })
    }
}

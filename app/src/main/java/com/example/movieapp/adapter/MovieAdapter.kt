package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.model.MovieModel
import kotlinx.android.synthetic.main.item_list_movie.view.*

/**
 * Create by Linh Le H. M. on 11/30/20
 */
class MovieAdapter(private var movieList: MutableList<MovieModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.onBindData(position)
        }
    }

    override fun getItemCount() = movieList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindData(position: Int) {
            val movieItem = movieList[position]
            val imgMovie = itemView.imgMovie
            val tvRate = itemView.tvRate
            val tvMovie = itemView.tvMovie
            movieItem.let {
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w1280" + it.posterPath)
                    .into(imgMovie)
                tvMovie.text = it.title
                tvRate.text = it.voteAverage.toString()
            }
        }
    }
}

package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Create by Linh Le H. M. on 11/24/20
 */
data class MovieModel(
    @SerializedName("id") var id: Int,
    @SerializedName("vote_count") var voteCount: Int,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_title") var originalTitle: String,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("video") var video: Boolean,
    @SerializedName("adult") var adult: Boolean
)

data class MoviePages(
    @SerializedName("results") val results: MutableList<MovieModel>
)

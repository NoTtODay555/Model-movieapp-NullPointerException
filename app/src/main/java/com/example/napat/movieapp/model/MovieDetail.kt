package com.example.napat.movieapp.model

import android.os.Parcel
import android.os.Parcelable


@Suppress("UNREACHABLE_CODE")
data class MovieDetail(
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Int,
    val homepage: String,
    val id: Int,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
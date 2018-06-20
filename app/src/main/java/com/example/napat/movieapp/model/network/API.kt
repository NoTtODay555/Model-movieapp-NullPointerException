package com.example.napat.movieapp.model.network

import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET ("movie/{id}?language=en-US&api_key=7bc5dd5396cab633f6b86f3e8fee0384")
    fun getMovieDetail(@Query("id") id : Int) : Call<MovieDetail>

    @GET("/movie/popular?api_key=7bc5dd5396cab633f6b86f3e8fee0384&language=en-US")
    fun getPopularMovie() : Call<PopularMovie>
}
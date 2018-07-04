package com.example.napat.movieapp.model.network

import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET ("movie/{id}?api_key=7bc5dd5396cab633f6b86f3e8fee0384&language=en-US")
    fun getMovieDetail(@Path("id") id : Int) : Observable<MovieDetail>

    @GET("movie/popular?api_key=7bc5dd5396cab633f6b86f3e8fee0384&language=en-US")
    fun getPopularMovie(@Query("page") page : Int) : Observable<PopularMovie>

    @GET("movie/top_rated?api_key=7bc5dd5396cab633f6b86f3e8fee0384&language=en-US")
    fun getTopRateMovie(@Query("page") page : Int) : Observable<PopularMovie>

    @GET("search/movie?api_key=7bc5dd5396cab633f6b86f3e8fee0384&language=en-US&page=1&include_adult=false")
    fun getSearchMovie(@Query("page") page: Int, @Query( "query") query : String) : Call<PopularMovie>

    @GET("movie/{id}/credits?api_key=7bc5dd5396cab633f6b86f3e8fee0384")
    fun getActor(@Path("id") id : Int) : Observable<ActorDetail>

}
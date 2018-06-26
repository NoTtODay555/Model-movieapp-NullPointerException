package com.example.napat.movieapp.InterActor

import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.network.API
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.model.network.Retrofit
import com.example.napat.movieapp.precenter.ConstructorPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InterActorRecycle(val popularPrecenter: ConstructorPresenter.RecyclableMovie) :
    ConductorInterActor.GetApiRecycle {
    override fun getSearchApi(page: Int, word: String) {
        var searchRatrofit = Retrofit().retrofit(BaseUrl.baseUrl)
            .create(API::class.java).getSearchMovie(page, word)
        searchRatrofit.enqueue(object : Callback<PopularMovie> {
            override fun onFailure(call: Call<PopularMovie>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<PopularMovie>?, response: Response<PopularMovie>?) {
                response?.body()?.let {

                    popularPrecenter.getMovieList(response.body())
                }
            }
        })
    }

    override fun getPopularApi(page: Int, type: Int) {
        when (type) {
            1 -> {
                val popularRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                    .create(API::class.java).getPopularMovie(page)
                popularRetrofit.enqueue(object : Callback<PopularMovie> {
                    override fun onFailure(call: Call<PopularMovie>?, t: Throwable?) {}
                    override fun onResponse(
                        call: Call<PopularMovie>?,
                        response: Response<PopularMovie>?
                    ) {
                        response?.body()?.let {
                            popularPrecenter.getMovieList(response.body())
                        }
                    }
                })
            }
            2 -> {
                val topRateRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                    .create(API::class.java).getTopRateMovie(page)
                topRateRetrofit.enqueue(object : Callback<PopularMovie> {
                    override fun onFailure(call: Call<PopularMovie>?, t: Throwable?) {}
                    override fun onResponse(
                        call: Call<PopularMovie>?,
                        response: Response<PopularMovie>?
                    ) {
                        response?.body()?.let {
                            popularPrecenter.getMovieList(response.body())
                        }
                    }
                })
            }

        }
    }
}
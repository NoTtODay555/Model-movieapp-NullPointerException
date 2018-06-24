package com.example.napat.movieapp.InterActor

import android.util.Log
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.network.API
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.model.network.Retrofit
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InterActorRecycle (val popularPrecenter : ConstutorPrecenter.RecycleviewMovie): ConstutorInterActor.GetApiRecycle{
    override fun getSearchApi(page: Int, word: String) {
        var searchRatrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API :: class.java).getSearchMovie(page,word)
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

    override fun getpopularapi(page: Int, type: Int) {
        var popularRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API :: class.java).getPopularMovie(page)
        var topRateRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API :: class.java).getTopRateMovie(page)
        when(type){
            1 -> {
                popularRetrofit.enqueue(object : Callback<PopularMovie> {
                    override fun onFailure(call: Call<PopularMovie>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<PopularMovie>?, response: Response<PopularMovie>?) {
                        response?.body()?.let {
                            popularPrecenter.getMovieList(response.body())
                        }
                    }

                })
            }
            2 -> {
                topRateRetrofit.enqueue(object : Callback<PopularMovie> {
                    override fun onFailure(call: Call<PopularMovie>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<PopularMovie>?, response: Response<PopularMovie>?) {
                        response?.body()?.let {
                            popularPrecenter.getMovieList(response.body())
                        }
                    }
            })
        }

    }
}
}
package com.example.napat.movieapp.main.conteck

import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.network.API
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.model.network.Retrofit
import com.example.napat.movieapp.show.presenter.ConsPresenterShow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InterActorRecycle(val popularPresenter: ConsPresenterShow.RecyclableMovie) :
        ConsInterAct.GetApiRecycle {
    override fun getSearchApi(page: Int, word: String) {
        val searchRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
            .create(API::class.java).getSearchMovie(page, word)
        searchRetrofit.enqueue(object : Callback<PopularMovie> {
            override fun onFailure(call: Call<PopularMovie>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<PopularMovie>?, response: Response<PopularMovie>?) {
                response?.body()?.let {
                    popularPresenter.getMovieList(response.body())
                }
            }
        })
    }

    override fun getPopularApi(page: Int, type: Int) {
        when (type) {
            1 -> {
                val popularRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                    .create(API::class.java).getPopularMovie(page)
                popularRetrofit.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            popularPresenter.getMovieList(it)
                        }
            }
            2 -> {
                val topRateRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                    .create(API::class.java).getTopRateMovie(page)
                topRateRetrofit.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            popularPresenter.getMovieList(it)
                        }
            }
        }
    }

}
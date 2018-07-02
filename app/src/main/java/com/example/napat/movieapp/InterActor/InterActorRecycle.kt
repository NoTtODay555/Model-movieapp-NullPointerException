package com.example.napat.movieapp.InterActor

import android.util.Log
import com.example.napat.movieapp.model.*
import com.example.napat.movieapp.model.network.API
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.model.network.Retrofit
import com.example.napat.movieapp.precenter.ConstructorPresenter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class InterActorRecycle(val popularPrecenter: ConstructorPresenter.RecyclableMovie) :
        ConductorInterActor.GetApiRecycle {
    override fun getSearchApi(page: Int, word: String) {
        val searchRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API::class.java).getSearchMovie(page, word)
        searchRetrofit.subscribeOn(Schedulers.io())
                .subscribe {
                    Log.e("Popular",it.toString())
                    popularPrecenter.getMovieList(it)
                }
    }

    override fun getPopularApi(page: Int, type: Int) {
        when (type) {
            1 -> {
                val popularRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                        .create(API::class.java).getPopularMovie(page)
                popularRetrofit.subscribeOn(Schedulers.io())
                        .subscribe {
                            Log.e("Popular",it.toString())
                            popularPrecenter.getMovieList(it)
                        }
            }
            2 -> {
                val topRateRetrofit = Retrofit().retrofit(BaseUrl.baseUrl)
                        .create(API::class.java).getTopRateMovie(page)
                topRateRetrofit.subscribeOn(Schedulers.io())
                        .subscribe{
                            Log.e("Popular",it.toString())
                            popularPrecenter.getMovieList(it)
                        }


            }

        }
    }

    private fun testis(id: Int): Observable<ActorDetail> {
        return Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API::class.java).getActor_test(id)

    }

    private fun testbed(id: Int): Observable<MovieDetail> {
        return Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API::class.java).getMovieDetailxx(id)

    }

    fun xxx(id: Int) {
        val a = listOf<Cast>()
        var b = listOf<MovieDetail>()
        Log.e("Actor", a.toString())
        Observable.zip(
                testis(id),
                testbed(id)
                , BiFunction<ActorDetail
                , MovieDetail,Detail> { ac: ActorDetail
                                        , mo: MovieDetail ->
            return@BiFunction Detail(ac,mo)
        }).subscribeOn(Schedulers.io()).subscribe(object : Observer<Detail> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Detail) {
                Log.e("Detail",t.toString())
            }

            override fun onError(e: Throwable) {}
        })
    }
}

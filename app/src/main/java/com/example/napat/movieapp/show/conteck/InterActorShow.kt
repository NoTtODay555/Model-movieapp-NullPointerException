package com.example.napat.movieapp.show.conteck

import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.network.API
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.model.network.Detail
import com.example.napat.movieapp.model.network.Retrofit
import com.example.napat.movieapp.show.presenter.ConsPresenterShow
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class InterActorShow(private val presenter : ConsPresenterShow.DetailShow): ConsInterActShow.GetApiDetail {
    private fun getActor(id: Int): Observable<ActorDetail> {
       return Retrofit().retrofit(BaseUrl.baseUrl).create(API :: class.java).getActor(id)
    }
    private fun getMovie(id: Int): Observable<MovieDetail> {
        return Retrofit().retrofit(BaseUrl.baseUrl).create(API :: class.java).getMovieDetail(id)
    }

    override fun getDetail(id: Int){
        Observable.zip(getActor(id),getMovie(id), BiFunction<ActorDetail,MovieDetail,Detail>{ac : ActorDetail,mo ->
            return@BiFunction Detail(ac,mo)
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.setDataDetail(it)
                }
    }
}
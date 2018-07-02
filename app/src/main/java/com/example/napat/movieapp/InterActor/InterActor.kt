package com.example.napat.movieapp.InterActor

import android.util.Log
import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.network.API
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.model.network.Retrofit
import com.example.napat.movieapp.precenter.ConstructorPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InterActor(val presenter : ConstructorPresenter.Main) : ConductorInterActor.GetApi {
    override fun getActorList(id: Int) {
        Log.e("getAPI",id.toString())
        Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API :: class.java).getActor(id)
                .subscribeOn(Schedulers.io())
                .subscribe{
                    presenter.showActorList(it)
                }

    }

    override fun getApi(id: Int){

        Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API :: class.java).getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .subscribe{
                    presenter.showApiTextId(it)
                }
    }
}

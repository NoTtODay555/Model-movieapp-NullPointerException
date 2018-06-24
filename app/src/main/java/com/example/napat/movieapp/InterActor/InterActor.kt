package com.example.napat.movieapp.InterActor

import android.util.Log
import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.network.API
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.model.network.Retrofit
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InterActor(val precenter : ConstutorPrecenter.Main) : ConstutorInterActor.GetApi {
    override fun getActorList(id: Int) {
        Log.e("getAPI",id.toString())
        Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API :: class.java).getActor(id)
                .enqueue(object : Callback<ActorDetail>{
                    override fun onFailure(call: Call<ActorDetail>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<ActorDetail>?, response: Response<ActorDetail>?) {
                        response?.body()?.let {
                            precenter.showAvtorList(it)
                        }
                    }

                })
    }

    override fun getapi(id: Int){

        Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API :: class.java).getMovieDetail(id)
                .enqueue(object : Callback<MovieDetail>{
                    override fun onFailure(call: Call<MovieDetail>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<MovieDetail>?, response: Response<MovieDetail>?) {
                        response?.body()?.let {
                            precenter.showapitextid(it)
                        }
                    }

                })
    }

}
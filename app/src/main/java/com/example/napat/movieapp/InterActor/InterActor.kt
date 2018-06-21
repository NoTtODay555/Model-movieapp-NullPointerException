package com.example.napat.movieapp.InterActor

import android.util.Log
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

    override fun getapi(id: Int){
        Log.e("getAPI",id.toString())
        Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API :: class.java).getMovieDetail(id+1)
                .enqueue(object : Callback<MovieDetail>{
                    override fun onFailure(call: Call<MovieDetail>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<MovieDetail>?, response: Response<MovieDetail>?) {
                        response?.body()?.original_title?.let {
                            Log.e("onresponse",it)
                            precenter.showapitextid(it)
                        }
                    }

                })
    }

}
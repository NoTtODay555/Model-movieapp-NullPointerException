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
    override fun getpopularapi(page: Int) {
        Log.e("popular",page.toString())
        Retrofit().retrofit(BaseUrl.baseUrl)
                .create(API :: class.java).getPopularMovie(page)
                .enqueue(object : Callback<PopularMovie> {
                    override fun onFailure(call: Call<PopularMovie>?, t: Throwable?) {
                        Log.e("onresponse",t.toString())
                    }

                    override fun onResponse(call: Call<PopularMovie>?, response: Response<PopularMovie>?) {
                        response?.body()?.let {
                            Log.e("onresponse",it.toString())
                            popularPrecenter.getMovieList(response.body())
                        }
                    }

                })
    }
}
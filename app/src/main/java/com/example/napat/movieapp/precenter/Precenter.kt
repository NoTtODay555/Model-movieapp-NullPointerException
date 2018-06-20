package com.example.napat.movieapp.precenter

import android.util.Log
import com.example.napat.movieapp.InterActor.ConstutorInterActor
import com.example.napat.movieapp.InterActor.InterActor
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result
import com.example.napat.movieapp.view.Constutor_View
import com.example.napat.movieapp.view.RecycleviewAdapter

class Precenter(val view : Constutor_View.getApiMovieDetail,val adapterView : Constutor_View.getApiPopularMovie) : ConstutorPrecenter.Main,
        ConstutorPrecenter.RecycleviewMovie {
    override fun pullMovielist() {
        interactor.getpopularapi()
    }

    override fun getMovieList(data: PopularMovie?) {
//        adapterView.getMovieList(data)
    }

    var interactor : ConstutorInterActor.GetApi = InterActor(this,this)
    override fun showapitextid(a: String) {
        view.showText(a)
    }

    override fun getId(id: Int) {
        Log.e("getid",id.toString())
       interactor.getapi(id)
    }
}
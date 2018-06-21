package com.example.napat.movieapp.precenter

import android.util.Log
import com.example.napat.movieapp.InterActor.ConstutorInterActor
import com.example.napat.movieapp.InterActor.InterActor
import com.example.napat.movieapp.InterActor.InterActorRecycle
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result
import com.example.napat.movieapp.view.Constutor_View
import com.example.napat.movieapp.view.RecycleviewAdapter

class PrecenterMain(val view : Constutor_View.getApiMovieDetail) : ConstutorPrecenter.Main{
    val interactor : ConstutorInterActor.GetApi = InterActor(this)

    override fun showapitextid(a: String) {
        view.showText(a)
    }

    override fun getId(id: Int) {
        Log.e("getid",id.toString())
       interactor.getapi(id)
    }
}
package com.example.napat.movieapp.precenter

import android.util.Log
import com.example.napat.movieapp.InterActor.ConstutorInterActor
import com.example.napat.movieapp.InterActor.InterActor
import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.view.Constutor_View

class PrecenterMain(val view : Constutor_View.getApitext) : ConstutorPrecenter.Main{
    override fun getIdActor(id: Int) {
        Log.e("getid",id.toString())
        interactor.getActorList(id)
    }

    override fun showAvtorList(a: ActorDetail) {
        view.listActor(a)
    }

    val interactor : ConstutorInterActor.GetApi = InterActor(this)

    override fun showapitextid(a: MovieDetail) {
        view.showText(a)
    }

    override fun getId(id: Int) {

       interactor.getapi(id)
    }
}
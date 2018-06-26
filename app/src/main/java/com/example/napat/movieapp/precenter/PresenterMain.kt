package com.example.napat.movieapp.precenter

import android.util.Log
import com.example.napat.movieapp.InterActor.ConductorInterActor
import com.example.napat.movieapp.InterActor.InterActor
import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.view.Constutor_View

class PresenterMain(val view : Constutor_View.getApitext) : ConstructorPresenter.Main{
    private val interactor : ConductorInterActor.GetApi = InterActor(this)
    override fun getIdActor(id: Int) {
        Log.e("getid",id.toString())
        interactor.getActorList(id)
    }

    override fun showActorList(a: ActorDetail) {
        view.listActor(a)
    }

    override fun showApiTextId(a: MovieDetail) {
        view.showText(a)
    }

    override fun getId(id: Int) {
       interactor.getApi(id)
    }
}
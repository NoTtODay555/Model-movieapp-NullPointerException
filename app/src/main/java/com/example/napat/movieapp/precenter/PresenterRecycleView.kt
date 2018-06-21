package com.example.napat.movieapp.precenter

import android.util.Log
import com.example.napat.movieapp.InterActor.ConstutorInterActor
import com.example.napat.movieapp.InterActor.InterActor
import com.example.napat.movieapp.InterActor.InterActorRecycle
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.view.Constutor_View

class PresenterRecycleView (val adapterView : Constutor_View.getPageApi): ConstutorPrecenter.RecycleviewMovie {
    var interactor : ConstutorInterActor.GetApiRecycle = InterActorRecycle(this)
    override fun pullpage(pageNumber: Int) {
        Log.e("pullpage",pageNumber.toString())
        interactor.getpopularapi(pageNumber)
    }

    override fun getMovieList(data: PopularMovie?) {
        Log.e("getmovieList",data.toString())
        data?.let { adapterView.getPageList(it)}
    }
}
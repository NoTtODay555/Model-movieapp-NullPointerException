package com.example.napat.movieapp.precenter

import android.util.Log
import com.example.napat.movieapp.InterActor.ConstutorInterActor
import com.example.napat.movieapp.InterActor.InterActor
import com.example.napat.movieapp.InterActor.InterActorRecycle
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.view.Constutor_View

class PresenterRecycleView (val adapterView : Constutor_View.getPageApi): ConstutorPrecenter.RecycleviewMovie {
    override fun pullSearchword(pageNumber: Int, word: String) {
        Log.e("pullSearch",pageNumber.toString())
        interactor.getSearchApi(pageNumber,word)
    }

    var interactor : ConstutorInterActor.GetApiRecycle = InterActorRecycle(this)
    override fun pullpage(pageNumber: Int, type: Int) {
        Log.e("pullpage",pageNumber.toString())
        interactor.getpopularapi(pageNumber,type)
    }

    override fun getMovieList(data: PopularMovie?) {
        data?.let { adapterView.getPageList(it)}
    }
}
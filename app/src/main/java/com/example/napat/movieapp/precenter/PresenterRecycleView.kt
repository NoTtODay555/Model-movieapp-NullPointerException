package com.example.napat.movieapp.precenter

import android.util.Log
import com.example.napat.movieapp.InterActor.ConductorInterActor
import com.example.napat.movieapp.InterActor.InterActorRecycle
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.view.Constutor_View

class PresenterRecycleView (private val adapterView : Constutor_View.getPageApi): ConstructorPresenter.RecyclableMovie {
    override fun pullSearchword(pageNumber: Int, word: String) {
        Log.e("pullSearch",pageNumber.toString())
        interactor.getSearchApi(pageNumber,word)
    }

    var interactor : ConductorInterActor.GetApiRecycle = InterActorRecycle(this)
    override fun pullpage(pageNumber: Int, type: Int) {
        Log.e("pullpage",pageNumber.toString())
        interactor.getPopularApi(pageNumber,type)
    }

    override fun getMovieList(data: PopularMovie?) {
        data?.let { adapterView.getPageList(it)}
    }
}
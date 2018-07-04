package com.example.napat.movieapp.main.presenter

import android.util.Log
import com.example.napat.movieapp.main.conteck.ConsInterAct
import com.example.napat.movieapp.main.conteck.InterActorRecycle
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.main.view.ConstuterViewMain
import com.example.napat.movieapp.show.presenter.ConsPresenterShow

class PresenterRecycleView (private val adapterView : ConstuterViewMain.getApiPage): ConsPresenterShow.RecyclableMovie {
    override fun pullSearchword(pageNumber: Int, word: String) {
        Log.e("pullSearch",pageNumber.toString())
        interactor.getSearchApi(pageNumber,word)
    }

    var interactor : ConsInterAct.GetApiRecycle = InterActorRecycle(this)
    override fun pullpage(pageNumber: Int, type: Int) {
        Log.e("pullpage",pageNumber.toString())
        interactor.getPopularApi(pageNumber,type)
    }

    override fun getMovieList(data: PopularMovie?) {
        data?.let { adapterView.getPageList(it)}
    }
}
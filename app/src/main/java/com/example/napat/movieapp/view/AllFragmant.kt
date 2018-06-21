package com.example.napat.movieapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import com.example.napat.movieapp.precenter.PresenterRecycleView
import kotlinx.android.synthetic.main.fragment_popular_fagmant.*



class AllFragmant  : Fragment()
        ,Constutor_View.getPageApi,
    Constutor_View.OnLoadMoreListener{
    var num : Int = 1
    var type : Int = 0
    private var popularMovie : ArrayList<Result> = arrayListOf()

    val recycleviewAdapter : RecycleviewAdapter  = RecycleviewAdapter(popularMovie)
    val presenterpopular: ConstutorPrecenter.RecycleviewMovie = PresenterRecycleView(this)
    override fun getPageList(data: PopularMovie) {
        Log.e("getList",data.toString())
        popularMovie.addAll(data.results)
        recycleviewAdapter.getlist(popularMovie)
    }

    override fun onLoadMore() {
        num += 1
        presenterpopular.pullpage(num)
    }

    // TODO: Rename and change types of parameters


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenterpopular.pullpage(num)
        recycleviewAdapter.setOnLoadMoreListener(this)
        return inflater.inflate(R.layout.fragment_popular_fagmant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_listMovie?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recycleviewAdapter
        }
    }



}

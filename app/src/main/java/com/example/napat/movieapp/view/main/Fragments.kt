package com.example.napat.movieapp.view.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result
import com.example.napat.movieapp.precenter.ConstructorPresenter
import com.example.napat.movieapp.precenter.PresenterRecycleView
import com.example.napat.movieapp.view.Constutor_View
import kotlinx.android.synthetic.main.fragment_popular_fagmant.*
private const val ARG_PARAM1 = "param1"
private const val WORD = "word"

@SuppressLint("ValidFragment")

class Fragments (context: Context) : Fragment()
        , Constutor_View.getPageApi,
        Constutor_View.OnLoadMoreListener {
    private var param1: Int? = null
    private var word : String? = null
    var num : Int = 1
    private var popularMovie : ArrayList<Result> = arrayListOf()
    private val recyclableAdapter : RecycleViewAdapter = RecycleViewAdapter(popularMovie, context)
    private val presenterPopular: ConstructorPresenter.RecyclableMovie = PresenterRecycleView(this)
    override fun getPageList(data: PopularMovie) {
        popularMovie.addAll(data.results)
        recyclableAdapter.getList(popularMovie)
    }

    override fun onLoadMore() {
        num += 1
        if (param1 == 3) word?.let { presenterPopular.pullSearchword(num, it) }
        else param1?.let { presenterPopular.pullpage(num, it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            word = it.getString(WORD)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (param1 == 3) word?.let { presenterPopular.pullSearchword(num, it) }
        else param1?.let { presenterPopular.pullpage(num, it) }
        recyclableAdapter.setOnLoadMoreListener(this)
        return inflater.inflate(R.layout.fragment_popular_fagmant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_listMovie?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclableAdapter
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: Int,word : String,context: Context) =
                Fragments(context).apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, param1)
                        putString(WORD,word)
                    }
                }
    }
}

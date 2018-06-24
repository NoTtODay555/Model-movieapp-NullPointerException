package com.example.napat.movieapp.view.main

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.napat.movieapp.view.Constutor_View
import kotlinx.android.synthetic.main.fragment_popular_fagmant.*
private const val ARG_PARAM1 = "param1"
private const val WORD = "word"

@SuppressLint("ValidFragment")

class Fragmant (context: Context) : Fragment()
        , Constutor_View.getPageApi,
        Constutor_View.OnLoadMoreListener {
    private var param1: Int? = null
    private var word : String? = null

    var num : Int = 1
    private var popularMovie : ArrayList<Result> = arrayListOf()
    private val recyclableAdapter : RecycleviewAdapter = RecycleviewAdapter(popularMovie, context)
    private val presenterPopular: ConstutorPrecenter.RecycleviewMovie = PresenterRecycleView(this)
    override fun getPageList(data: PopularMovie) {
        Log.e("getList",data.toString())
        popularMovie.addAll(data.results)
        recyclableAdapter.getlist(popularMovie)
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


    // TODO: Rename and change types of parameters


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.e("fragment",param1.toString()+word)
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int,word : String,context: Context) =
                Fragmant(context).apply {
                    Log.e("Lodeword",word)
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, param1)
                        putString(WORD,word)
                    }
                }
    }



}

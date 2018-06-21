package com.example.napat.movieapp.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import com.example.napat.movieapp.precenter.PrecenterMain
import com.example.napat.movieapp.precenter.PresenterRecycleView

class RecycleviewAdapter(var popularMovie: List<Result>) : RecyclerView.Adapter<ViewHolder>() {
    private var onLoadMoreListener: Constutor_View.OnLoadMoreListener? = null
    fun setOnLoadMoreListener(mOnLoadMoreListener: Constutor_View.OnLoadMoreListener) {
        onLoadMoreListener = mOnLoadMoreListener
    }
    fun getlist (data : List<Result>) {
        popularMovie = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cradview,parent,false))
    }

    override fun getItemCount(): Int {
       return popularMovie?.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position+1 == popularMovie.size) {
            Log.e("Max", popularMovie.size.toString())
            onLoadMoreListener?.onLoadMore()
        }
        return holder.onBindData(popularMovie?.get(position))
    }

}
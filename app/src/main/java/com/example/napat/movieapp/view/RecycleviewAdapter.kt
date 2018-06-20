package com.example.napat.movieapp.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import kotlinx.android.synthetic.main.cradview.view.*

class RecycleviewAdapter(val context: Context) : RecyclerView.Adapter<ViewHolder>()
         ,Constutor_View.getApiPopularMovie{
    var popolarmovie : PopularMovie? = null
    override fun getMovieList(data : PopularMovie){
        popolarmovie = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cradview,parent,false))
    }

    override fun getItemCount(): Int {
       return popolarmovie?.results?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindData(popolarmovie!!.results[position])
    }

}
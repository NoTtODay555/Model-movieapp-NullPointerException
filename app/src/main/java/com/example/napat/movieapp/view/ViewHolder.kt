package com.example.napat.movieapp.view

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result
import com.example.napat.movieapp.model.network.BaseUrl
import kotlinx.android.synthetic.main.cradview.view.*
import com.example.napat.movieapp.view.Constutor_View.OnLoadMoreListener



class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun onBindData(list : Result?) {
        view.titel_movie.text = list?.title
        Glide.with(view).load(BaseUrl.baseUrlImageMovie+list?.backdrop_path).into(view.cv_movie_rv_view)
        }


    }

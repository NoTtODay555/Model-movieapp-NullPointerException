package com.example.napat.movieapp.view

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result
import kotlinx.android.synthetic.main.cradview.view.*

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun onBindData(list : Result?) {
        view.titel_movie.text = list?.title
        }
    }
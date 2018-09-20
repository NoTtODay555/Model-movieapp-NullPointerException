package com.example.napat.movieapp.history.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.show.view.ShowMovie
import kotlinx.android.synthetic.main.cradview.view.*

class ViewHoderHistory (val view: View, val context: Context): RecyclerView.ViewHolder(view) {
    fun onBindData(list : ListDataViweHolder?) {
        view.titel_movie.text = list?.title
        val optional = RequestOptions().error(R.mipmap.ic_launcher_round)
        Glide.with(view).load(BaseUrl.baseUrlImageMovie+list?.imageUrl).apply(optional).into(view.cv_movie_rv_view)
        view.setOnClickListener {
            context.startActivity(Intent(context, ShowMovie:: class.java).putExtra("IdMovieDetail", list?.id))
        }


    }
}
package com.example.napat.movieapp.view.favorite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.Result
import com.example.napat.movieapp.view.Constutor_View
import com.example.napat.movieapp.view.main.ViewHolder

class RecycleFavorite(var favoriteMovie: List<MovieDetail>,val context: Context): RecyclerView.Adapter<ViewHoderFavorite>() {
    private var onLoadMoreListener: Constutor_View.OnLoadMoreListener? = null
    fun setOnLoadMoreListener(mOnLoadMoreListener: Constutor_View.OnLoadMoreListener) {
        onLoadMoreListener = mOnLoadMoreListener
    }
    fun getlist (data : List<MovieDetail>) {
        Log.e("data",data.toString())
        favoriteMovie = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoderFavorite {
        val recyclableAdapter = RecycleFavorite(favoriteMovie, context)
        onLoadMoreListener?.let { recyclableAdapter.setOnLoadMoreListener(it) }
        return ViewHoderFavorite(LayoutInflater.from(parent.context).inflate(R.layout.cradview, parent, false), context)
    }

    override fun getItemCount(): Int {
        return favoriteMovie?.size
    }

    override fun onBindViewHolder(holder: ViewHoderFavorite, position: Int) {
        if(position+1 == favoriteMovie.size) {
            onLoadMoreListener?.onLoadMore()
        }
        return holder.onBindData(favoriteMovie?.get(position))
    }
}
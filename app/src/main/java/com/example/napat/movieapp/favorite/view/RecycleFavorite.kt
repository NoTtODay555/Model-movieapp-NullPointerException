package com.example.napat.movieapp.favorite.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.main.view.ConstuterViewMain

class RecycleFavorite(private var favoriteMovie: ArrayList<ListDataViweHolder>?, val context: Context): RecyclerView.Adapter<ViewHoderFavorite>() {
    private var onLoadMoreListener: ConstuterViewMain.OnLoadMoreListener? = null
    private fun setOnLoadMoreListener(mOnLoadMoreListener: ConstuterViewMain.OnLoadMoreListener) {
        onLoadMoreListener = mOnLoadMoreListener
    }
    fun getList (data : ArrayList<ListDataViweHolder>?) {
        Log.e("data",data.toString())
        favoriteMovie = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoderFavorite {
        val recyclableAdapter = RecycleFavorite(this.favoriteMovie, context)
        onLoadMoreListener?.let { recyclableAdapter.setOnLoadMoreListener(it) }
        return ViewHoderFavorite(LayoutInflater.from(parent.context).inflate(R.layout.cradview, parent, false), context)
    }

    override fun getItemCount(): Int {
        return this.favoriteMovie?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHoderFavorite, position: Int) {
        if(position+1 == favoriteMovie?.size ?: 0) {
            onLoadMoreListener?.onLoadMore()
        }
        return holder.onBindData(favoriteMovie?.get(position))
    }
}
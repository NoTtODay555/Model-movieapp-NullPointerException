package com.example.napat.movieapp.main.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.Result

class RecycleViewAdapter(var popularMovie: List<Result>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private var onLoadMoreListener: ConstuterViewMain.OnLoadMoreListener? = null
    fun setOnLoadMoreListener(mOnLoadMoreListener: ConstuterViewMain.OnLoadMoreListener) {
        onLoadMoreListener = mOnLoadMoreListener
    }
    fun getList (data : List<Result>) {
        popularMovie = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cradview, parent, false), context)
    }

    override fun getItemCount(): Int {
       return popularMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position+1 == popularMovie.size) {
            onLoadMoreListener?.onLoadMore()
        }
        return holder.onBindData(popularMovie.get(position))
    }
    

}
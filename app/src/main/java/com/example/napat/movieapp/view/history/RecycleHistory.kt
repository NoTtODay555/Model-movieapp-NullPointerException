package com.example.napat.movieapp.view.history

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.HistoryData
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.view.Constutor_View
import com.example.napat.movieapp.view.favorite.RecycleFavorite
import com.example.napat.movieapp.view.favorite.ViewHoderFavorite

class RecycleHistory (private var historyMovie: List<HistoryData>, val context: Context): RecyclerView.Adapter<ViewHoderHistory>(){
    private var onLoadMoreListener: Constutor_View.OnLoadMoreListener? = null
    private fun setOnLoadMoreListener(mOnLoadMoreListener: Constutor_View.OnLoadMoreListener) {
        onLoadMoreListener = mOnLoadMoreListener
    }

    fun getList (data : ArrayList<HistoryData>) {
        Log.e("data",data.toString())
        historyMovie = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoderHistory {
        val recyclableAdapter = RecycleHistory(historyMovie, context)
        onLoadMoreListener?.let { recyclableAdapter.setOnLoadMoreListener(it) }
        return ViewHoderHistory(LayoutInflater.from(parent.context).inflate(R.layout.cradview, parent, false), context)
    }

    override fun getItemCount(): Int {
        return historyMovie.size
    }

    override fun onBindViewHolder(holder: ViewHoderHistory, position: Int) {
        if(position+1 == historyMovie.size) {
            onLoadMoreListener?.onLoadMore()
        }
        Log.e("position",position.toString())
        Log.e("new position",( (historyMovie.size-1 )-position).toString())
        return holder.onBindData(historyMovie[( historyMovie.size-1 )-position])
    }
}
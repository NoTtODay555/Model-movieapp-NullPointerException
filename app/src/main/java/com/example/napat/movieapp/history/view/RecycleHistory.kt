package com.example.napat.movieapp.history.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.main.view.ConstuterViewMain

class RecycleHistory (private var listMovieViweHolder: List<ListDataViweHolder>, val context: Context): RecyclerView.Adapter<ViewHoderHistory>(){
    private var onLoadMoreListener: ConstuterViewMain.OnLoadMoreListener? = null
    private fun setOnLoadMoreListener(mOnLoadMoreListener: ConstuterViewMain.OnLoadMoreListener) {
        onLoadMoreListener = mOnLoadMoreListener
    }

    fun getList (data : ArrayList<ListDataViweHolder>) {
        Log.e("data",data.toString())
        listMovieViweHolder = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoderHistory {
        val recyclableAdapter = RecycleHistory(listMovieViweHolder, context)
        onLoadMoreListener?.let { recyclableAdapter.setOnLoadMoreListener(it) }
        return ViewHoderHistory(LayoutInflater.from(parent.context).inflate(R.layout.cradview, parent, false), context)
    }

    override fun getItemCount(): Int {
        return listMovieViweHolder.size
    }

    override fun onBindViewHolder(holder: ViewHoderHistory, position: Int) {
        if(position+1 == listMovieViweHolder.size) {
            onLoadMoreListener?.onLoadMore()
        }
        Log.e("position",position.toString())
        Log.e("new position",( (listMovieViweHolder.size-1 )-position).toString())
        return holder.onBindData(listMovieViweHolder[( listMovieViweHolder.size-1 )-position])
    }
}
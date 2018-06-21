package com.example.napat.movieapp.view

import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result

interface Constutor_View {
    interface getApiMovieDetail{
        fun showText(a : String)
    }
    interface getPageApi{
        fun getPageList(data : PopularMovie)
    }
    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}
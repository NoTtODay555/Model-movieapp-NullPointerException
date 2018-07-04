package com.example.napat.movieapp.main.view

import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie

interface ConstuterViewMain {
    interface getApiPage{
        fun getPageList(data : PopularMovie)
    }
    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}
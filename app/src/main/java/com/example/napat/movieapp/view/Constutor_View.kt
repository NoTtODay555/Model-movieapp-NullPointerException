package com.example.napat.movieapp.view

import com.example.napat.movieapp.model.*

interface Constutor_View {
    interface getApitext{
        fun showText(a : MovieDetail)
        fun listActor( actor : ActorDetail)
    }
    interface getPageApi{
        fun getPageList(data : PopularMovie)
    }
    interface OnLoadMoreListener {
        fun onLoadMore()
    }
    interface CheckData{
        fun getId(favoriteid : ArrayList<Int>)
        fun getActor()
    }
    interface ShowTextFaverite{
        fun showFaveritetext(text : String,count : Int)
    }
}
package com.example.napat.movieapp.view

import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Rate

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
    interface ShowTextFaverite{
        fun showFaveritetext(text : String,count : Int,id : Int)
    }
    interface GetDataView{
        fun listViewData(listView: ListViewData?,id :Int)
    }
    interface GetDataFavorite{
        fun listFavoriteData(listFavorite: ArrayList<Int>?,id :Int,count: Int)
    }
    interface GetDataHistory{
        fun listHistoryData(listHistory : ArrayList<Int>?,id: Int)
    }
    interface GetDataRate{
        fun listRateData(listRate : ArrayList<Rate>?,id : Int,fl : Float)
    }
}
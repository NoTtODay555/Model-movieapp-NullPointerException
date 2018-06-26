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
        fun showFaveritetext(text : String,count : Int,id : Int)
    }
    interface GetDataView{
        fun listViewData(listView: ArrayList<ListViewData>?,id :Int)
    }
    interface GetDataFavorite{
        fun listFavoriteData(listFavorite: ArrayList<Int>?,id :Int,count: Int)
    }
    interface GetDataHistory{
        fun listHistoryData(listHistory : ArrayList<Int>?,id: Int)
    }
}
package com.example.napat.movieapp.show.presenter

import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Rate
import com.example.napat.movieapp.model.network.Detail

interface ConsPresenterShow {
    interface SetDataHis{
        fun setHistoryData(id: Int, title: String, imageUrl: String)
    }

    interface SetDataFavor{
        fun getFavoriteData(id: Int)
        fun setFavoriteData(id:Int,title : String,imageUrl : String,boo :Boolean)
    }

    interface DetailShow{
        fun getDataDetail(id: Int)
        fun setDataDetail(listDetail: Detail)
    }

    interface CheckFavoriteBT{
        fun checkButton(boo: Boolean)
    }

    interface RecyclableMovie {
        fun getMovieList(data: PopularMovie?)
        fun pullpage(pageNumber: Int,type : Int)
        fun pullSearchword(pageNumber: Int,word : String)
    }
    interface DataView{
        fun getViewData(id : Int)
        fun setViewData(id: Int, view: Int, list: ListViewData?)
        fun findIdInArray(list: ArrayList<ListViewData>, id: Int): Int
    }

    interface DataRate{
        fun getRateData(id : Int, fl : Float)
        fun setRateData(ist: ArrayList<Rate>?, id : Int, ratingPoint : ArrayList<Float>)
        fun findidInArray(list: ArrayList<Rate>, id: Int): Int
        fun sumArrayRate(list: ArrayList<Float>) : Float
    }

}
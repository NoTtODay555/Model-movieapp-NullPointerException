package com.example.napat.movieapp.precenter

import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Rate

interface ConstructorPresenter {
    interface Main {
        fun getId(id: Int)
        fun getIdActor(id:Int)
        fun showApiTextId(a: MovieDetail)
        fun showActorList(a: ActorDetail)


    }

    interface RecyclableMovie {
        fun getMovieList(data: PopularMovie?)
        fun pullpage(pageNumber: Int,type : Int)
        fun pullSearchword(pageNumber: Int,word : String)

    }
    interface SaveData{
        fun addData(id : Int)
    }
    interface DeleteData{
        fun removeData(Id : Int)
    }
    interface CheckFavoriteBT{
        fun checkButton(count :Int, id : Int)
    }
    interface DataView{
        fun getViewData(id : Int)
        fun setViewData(id: Int, view: Int, list: ListViewData?)
        fun findIdInArray(list: ArrayList<ListViewData>, id: Int): Int
    }
    interface DataFavorite{
        fun getFavoriteData(id : Int,count: Int)
        fun setFavoriteData(list: ArrayList<Int>?)
        fun findFavoriteInArray(list: ArrayList<Int>?,id : Int) : Int
    }
    interface DataHistory{
        fun getHistoryData(id : Int)
        fun setHistoryData(list: ArrayList<Int>?)
        fun findIdinArray(list: ArrayList<Int>?, id : Int) : Boolean
    }
    interface DataRate{
        fun getRateData(id : Int, fl : Float)
        fun setRateData(ist: ArrayList<Rate>?, id : Int, ratingPoint : ArrayList<Float>)
        fun findidInArray(list: ArrayList<Rate>, id: Int): Int
        fun sumArrayRate(list: ArrayList<Float>) : Float
    }

}

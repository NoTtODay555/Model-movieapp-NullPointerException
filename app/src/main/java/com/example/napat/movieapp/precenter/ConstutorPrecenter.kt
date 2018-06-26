package com.example.napat.movieapp.precenter

import com.example.napat.movieapp.model.*

interface ConstutorPrecenter {
    interface Main {
        fun getId(id: Int)
        fun getIdActor(id:Int)
        fun showapitextid(a: MovieDetail)
        fun showAvtorList(a: ActorDetail)


    }

    interface RecycleviewMovie {
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
    interface ChackFavoriteBT{
        fun ChackButton(count :Int,id : Int)
    }
    interface DataView{
        fun getViewData(id : Int)
        fun setViewData(id: Int, view: Int, list: ArrayList<ListViewData>?)
        fun findidInArray(list: ArrayList<ListViewData>, id: Int): Int
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

}

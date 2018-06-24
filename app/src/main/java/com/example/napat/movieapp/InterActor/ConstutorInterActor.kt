package com.example.napat.movieapp.InterActor

import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.network.API

interface ConstutorInterActor {
    interface GetApi{
        fun getapi(id:Int)
        fun getActorList(id: Int)

    }
    interface GetApiRecycle{
        fun getpopularapi(page: Int,type : Int)
        fun getSearchApi(page: Int,word : String)
    }
}
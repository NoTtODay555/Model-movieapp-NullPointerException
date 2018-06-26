package com.example.napat.movieapp.InterActor

import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.network.API

interface ConductorInterActor {
    interface GetApi{
        fun getApi(id:Int)
        fun getActorList(id: Int)
    }
    interface GetApiRecycle{
        fun getPopularApi(page: Int, type : Int)
        fun getSearchApi(page: Int,word : String)
    }
}
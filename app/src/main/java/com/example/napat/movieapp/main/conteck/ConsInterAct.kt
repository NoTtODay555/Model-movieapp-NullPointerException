package com.example.napat.movieapp.main.conteck

interface ConsInterAct {
    interface GetApiRecycle{
        fun getPopularApi(page: Int, type : Int)
        fun getSearchApi(page: Int,word : String)
    }
}
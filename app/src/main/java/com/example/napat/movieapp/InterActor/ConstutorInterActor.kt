package com.example.napat.movieapp.InterActor

import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.network.API

interface ConstutorInterActor {
    interface GetApi{
        fun getapi(id:Int)
        fun getpopularapi()
    }
}
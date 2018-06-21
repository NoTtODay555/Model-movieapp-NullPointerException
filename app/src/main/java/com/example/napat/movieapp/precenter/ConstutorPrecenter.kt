package com.example.napat.movieapp.precenter

import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result

interface ConstutorPrecenter {
    interface Main {
        fun getId(id: Int)
        fun showapitextid(a: String)

    }

    interface RecycleviewMovie {
        fun getMovieList(data: PopularMovie?)
        fun pullpage(pageNumber: Int)

    }
}

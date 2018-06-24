package com.example.napat.movieapp.precenter

import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result

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
        fun ChackButton(count :Int)
    }

}

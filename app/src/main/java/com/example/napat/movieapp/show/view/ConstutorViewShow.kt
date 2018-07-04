package com.example.napat.movieapp.show.view

import android.app.ProgressDialog
import android.content.Context
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.model.Rate
import com.example.napat.movieapp.model.network.Detail

interface ConstutorViewShow {
    interface ShowTextFavorite{
        fun showFavoriteText(text : String)
    }
    interface GetDataView{
        fun listViewData(listView: ListViewData?, id :Int)
    }

    interface GetDataRate{
        fun listRateData(listRate : ArrayList<Rate>?, id : Int, fl : Float)
    }
    interface GetDetail{
        fun listDetail(listDetail: Detail)
    }
    interface GetDataFavorite{
        fun listFavoriteData(listFavorite: ArrayList<ListDataViweHolder>?,favorite : Boolean)
    }

}
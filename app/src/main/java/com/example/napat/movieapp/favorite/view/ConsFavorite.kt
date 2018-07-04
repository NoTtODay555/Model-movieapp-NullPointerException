package com.example.napat.movieapp.favorite.view

import com.example.napat.movieapp.model.ListDataViweHolder

interface ConsFavorite {
    interface GetDataFavorite{
        fun listFavoriteData(listFavorite: ArrayList<ListDataViweHolder>?, favorite : Boolean)
    }
}
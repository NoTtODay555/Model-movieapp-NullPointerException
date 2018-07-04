package com.example.napat.movieapp.favorite.presenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.favorite.view.ConsFavorite
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

var NEWFAVORITE = "File Key Favorite"
var NEWDATALISTFAVORITE = "list_data_favorite"

@Suppress("DEPRECATION")
class GetFavorite(val context: Context, val show: ConsFavorite.GetDataFavorite) :
        PresenterFavorite.DataFavorite {
    override fun getFavoriteData(id: Int) {
        val preference = context.getSharedPreferences(NEWFAVORITE, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preference.getString(NEWDATALISTFAVORITE, test)
        val typeTokenFavorite = object : TypeToken<ArrayList<ListDataViweHolder>>() {}.type
        val listData: ArrayList<ListDataViweHolder>? = gson.fromJson(json, typeTokenFavorite)
        val listDataNew = listData ?: arrayListOf()
        listDataNew.let {
            Log.e("listFavorite_inPresente", listDataNew.toString())
            (listData?.filter { it.id == id })?.isNotEmpty()?.let { it1 -> show.listFavoriteData(listData, it1) }
        }
    }

}
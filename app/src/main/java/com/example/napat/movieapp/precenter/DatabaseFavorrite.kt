package com.example.napat.movieapp.precenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.view.Constutor_View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


var FAVORITE = "File Key"
var DATALISTFAVORITE = "list_data_favorite"
@Suppress("DEPRECATION")
class DatabaseFavorrite (val context: Context,val show : Constutor_View.GetDataFavorite) : ConstutorPrecenter.DataFavorite{
    override fun getFavoriteData(id : Int,count : Int) {
        val preferances = context.getSharedPreferences(FAVORITE, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferances.getString(DATALISTFAVORITE, test)
        val typeToken = object : TypeToken<ArrayList<Int>>() {}.type
        val listData : ArrayList<Int>?  = gson.fromJson(json, typeToken)
        var listDatanew = listData ?: arrayListOf()
        listDatanew.let {
            Log.e("listFavorite_inPresente",listDatanew.toString())
            show.listFavoriteData(it,id,count)
        }
    }

    override fun setFavoriteData(list: ArrayList<Int>?) {
        val preferances = context.getSharedPreferences(FAVORITE, Context.MODE_PRIVATE)
        val edit = preferances.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        Log.e("Json",json.toString())
        edit.putString(DATALISTFAVORITE, json)
        edit.apply()
    }

    override fun findFavoriteInArray(list: ArrayList<Int>?, id : Int) : Int {
        for (i in 0..((list?.size)?.minus(1) ?: 0)) {
            if (list?.get(i)  == id) {
                return 2
            }
        }
        return 1
    }

}
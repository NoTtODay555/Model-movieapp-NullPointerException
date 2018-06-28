package com.example.napat.movieapp.precenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.view.Constutor_View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

var FAVORITE = "File Key"
var DATALISTFAVORITE = "list_data_favorite"

@Suppress("DEPRECATION")
class DatabaseFavorite(val context: Context, val show: Constutor_View.GetDataFavorite) :
    ConstructorPresenter.DataFavorite {
    override fun getFavoriteData(id: Int) {
        val preference = context.getSharedPreferences(FAVORITE, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preference.getString(DATALISTFAVORITE, test)
        val typeToken = object : TypeToken<ArrayList<ListDataViweHolder>>() {}.type
        val listData: ArrayList<ListDataViweHolder>? = gson.fromJson(json, typeToken)
        var listDataNew = listData ?: arrayListOf()
        listDataNew.let {
            Log.e("listFavorite_inPresente", listDataNew.toString())
            (listData?.filter { it.id == id })?.isNotEmpty()?.let { it1 -> show.listFavoriteData(listData, it1) }
        }
    }

    override fun setFavoriteData(id:Int,title : String,imageUrl : String,boo :Boolean) {
        val preference = context.getSharedPreferences(FAVORITE, Context.MODE_PRIVATE)
        val edit = preference.edit()
        val keptList = getFavoriteDataInClass() ?: ArrayList()
        val filteredList = keptList.filter { it.id == id }
        val addItem = ListDataViweHolder(id,title,imageUrl)
        Log.e(" filteredList ", filteredList.toString())
        when (boo) {
            true -> {
                keptList.add(addItem)
            }
            else -> {
                for (b in 0 until keptList.size){
                    if (id == keptList[b].id) {
                        keptList.remove(keptList[b])
                    }
                }
            }
        }
        val gson = Gson()
        val json = gson.toJson(keptList)
        Log.e("Json", json.toString())
        edit.putString(DATALISTFAVORITE, json)
        edit.apply()
    }

    override fun findFavoriteInArray(list: ArrayList<Int>?, id: Int): Int {
        for (i in 0..((list?.size)?.minus(1) ?: 0)) {
            if (list?.get(i) == id) {
                return 2
            }
        }
        return 1
    }
     private fun getFavoriteDataInClass() : ArrayList<ListDataViweHolder>?{
         val preference = context.getSharedPreferences(FAVORITE, Context.MODE_PRIVATE)
         val test = ""
         val gson = Gson()
         val json: String = preference.getString(DATALISTFAVORITE, test)
         val typeToken = object : TypeToken<ArrayList<ListDataViweHolder>>() {}.type
         return gson.fromJson(json,typeToken)
     }
}
package com.example.napat.movieapp.show.presenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.favorite.presenter.NEWDATALISTFAVORITE
import com.example.napat.movieapp.favorite.presenter.NEWFAVORITE
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.show.view.ConstutorViewShow
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SetFavorite(var context: Context,var show : ConstutorViewShow.GetDataFavorite) : ConsPresenterShow.SetDataFavor {
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

    override fun setFavoriteData(id:Int,title : String,imageUrl : String,boo :Boolean) {
        val preference = context.getSharedPreferences(NEWFAVORITE, Context.MODE_PRIVATE)
        val edit = preference.edit()
        val favoriteList = getFavoriteDataInClass() ?: ArrayList()
        val filteredList = favoriteList.filter { it.id == id }
        val addItem = ListDataViweHolder(id,title,imageUrl)
        Log.e(" filteredList ", filteredList.toString())
        when (boo) {
            true -> {
                favoriteList.add(addItem)
            }
            false -> {
                for (b in 0 until favoriteList.size){
                    if (id == favoriteList[b].id) {
                        favoriteList.remove(favoriteList[b])
                        break
                    }
                }
            }
        }
        val gson = Gson()
        val json = gson.toJson(favoriteList)
        Log.e("Json", json.toString())
        edit.putString(NEWDATALISTFAVORITE, json)
        edit.apply()
    }

    private fun getFavoriteDataInClass() : ArrayList<ListDataViweHolder>?{
        val preference = context.getSharedPreferences(NEWFAVORITE, Context.MODE_PRIVATE)
        val gson = Gson()
        val json: String = preference.getString(NEWDATALISTFAVORITE,"")
        val typeToken = object : TypeToken<ArrayList<ListDataViweHolder>>() {}.type
        return gson.fromJson(json,typeToken) ?: arrayListOf()
    }
}
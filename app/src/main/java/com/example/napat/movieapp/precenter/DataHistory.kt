package com.example.napat.movieapp.precenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.view.Constutor_View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


var NEWHISTORY = "File Key History"
var NEWDATALISTHISTORY = "list_data_history"

class DataHistory(val context: Context,
                  private val history : Constutor_View.GetDataHistory)
                  : ConstructorPresenter.DataHistory{

    override fun getHistoryData() {
        val preferences = context.getSharedPreferences(NEWHISTORY, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferences.getString(NEWDATALISTHISTORY, test)
        val typeToken = object : TypeToken<ArrayList<ListDataViweHolder>>() {}.type
        val listData: ArrayList<ListDataViweHolder>? = gson.fromJson(json, typeToken)
        val listDataNew = listData ?: arrayListOf()
        listDataNew.let {
            Log.e("listFavorite_inPresente", listDataNew.toString())
            history.listHistoryData(it)
        }
    }

    override fun setHistoryData(id: Int, title: String, imageUrl: String) {
        val preferences = context.getSharedPreferences(NEWHISTORY, Context.MODE_PRIVATE)
        val edit = preferences.edit()
        val listHistory = getHistoryDataInClass() ?: ArrayList()
        Log.e(" listHistory ", listHistory.toString())
        val filteredList = listHistory.filter { it.id == id }
        val addHistory = ListDataViweHolder(id,title,imageUrl)
        when (filteredList.isEmpty()) {
            true -> {
                listHistory.add(addHistory)
                Log.e(" keptList ", listHistory.toString())
            }
            else -> {
                for (b in 0 until listHistory.size){
                    if (id == listHistory[b].id) {
                        listHistory.remove(listHistory[b])
                        listHistory.add(addHistory)
                    }
                }
                Log.e("preAdd", listHistory.toString())
            }
        }
        Log.e("Json", listHistory.toString())
        val gson = Gson()
        val json = gson.toJson(listHistory)
        Log.e("Json", json.toString())
        edit.putString(NEWDATALISTHISTORY, json)
        edit.apply()
    }

    override fun findIdinArray(list: ArrayList<Int>?, id : Int): Boolean {
        for(i in 0 until (list?.size ?: 0)){
            when {
                (list?.get(i) ?: 0 == id) ->  return true
            }
        }
        return false
    }
    private fun getHistoryDataInClass(): ArrayList<ListDataViweHolder>? {
        val preferences = context.getSharedPreferences(NEWHISTORY, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferences.getString(NEWDATALISTHISTORY, test)
        val typeToken = object : TypeToken<ArrayList<ListDataViweHolder>>() {}.type
        val listData: ArrayList<ListDataViweHolder>? = gson.fromJson(json, typeToken)
        Log.e("listData",listData.toString())
        return listData
    }
}
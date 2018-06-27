package com.example.napat.movieapp.precenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.model.HistoryData
import com.example.napat.movieapp.view.Constutor_View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


var HISTORY = "File Key"
var DATALISTHISTORY = "list_data_history"

class DataHistory(val context: Context,
                  private val history : Constutor_View.GetDataHistory)
                  : ConstructorPresenter.DataHistory{

    override fun getHistoryData() {
        val preferences = context.getSharedPreferences(HISTORY, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferences.getString(DATALISTHISTORY, test)
        val typeToken = object : TypeToken<ArrayList<HistoryData>>() {}.type
        val listData: ArrayList<HistoryData>? = gson.fromJson(json, typeToken)
        val listDataNew = listData ?: arrayListOf()
        listDataNew.let {
            Log.e("listFavorite_inPresente", listDataNew.toString())
            history.listHistoryData(it)
        }
    }

    override fun setHistoryData(id: Int, title: String, imageUrl: String) {
        val preferences = context.getSharedPreferences(HISTORY, Context.MODE_PRIVATE)
        val edit = preferences.edit()
        val listHistory = getHistoryDataInClass() ?: ArrayList()
        var i = 0
        Log.e(" listHistory ", listHistory.toString())
        val filteredList = listHistory.filter { it.id == id }
        val addHistory = HistoryData(id,title,imageUrl)
        when (filteredList.isEmpty()) {
            true -> {
                listHistory.add(addHistory)
                Log.e(" keptList ", listHistory.toString())
            }
            else -> {
                listHistory.forEach {
                    if (id == it.id) {
                        listHistory.remove(it)
                        Log.e("remove", listHistory.toString())
                    }
                }
                Log.e("preAdd", listHistory.toString())
                listHistory.add(addHistory)
            }
        }
        Log.e("Json", listHistory.toString())
        val gson = Gson()
        val json = gson.toJson(listHistory)
        Log.e("Json", json.toString())
        edit.putString(DATALISTHISTORY, json)
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
    private fun getHistoryDataInClass(): ArrayList<HistoryData>? {
        val preferences = context.getSharedPreferences(HISTORY, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferences.getString(DATALISTHISTORY, test)
        val typeToken = object : TypeToken<ArrayList<HistoryData>>() {}.type
        val listData: ArrayList<HistoryData>? = gson.fromJson(json, typeToken)
        Log.e("listData",listData.toString())
        return listData
    }
}
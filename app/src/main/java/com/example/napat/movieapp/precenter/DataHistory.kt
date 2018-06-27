package com.example.napat.movieapp.precenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.view.Constutor_View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


var HISTORY = "File Key"
var DATALISTHISTORY = "list_data_history"

class DataHistory(val context: Context, private val history : Constutor_View.GetDataHistory)  : ConstructorPresenter.DataHistory {
    override fun getHistoryData(id: Int) {
        val preferences = context.getSharedPreferences(HISTORY, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferences.getString(DATALISTHISTORY, test)
        val typeToken = object : TypeToken<ArrayList<Int>>() {}.type
        val listData: ArrayList<Int>? = gson.fromJson(json, typeToken)
        val listDataNew = listData ?: arrayListOf()
        listDataNew.let {
            Log.e("listFavorite_inPresente", listDataNew.toString())
            history.listHistoryData(it, id)
        }
    }

    override fun setHistoryData(list: ArrayList<Int>?) {
        val preferences = context.getSharedPreferences(HISTORY, Context.MODE_PRIVATE)
        val edit = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
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

}
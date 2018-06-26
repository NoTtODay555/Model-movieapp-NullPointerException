package com.example.napat.movieapp.precenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.view.Constutor_View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


var HISTORY = "File Key"
var DATALISTHISTORY = "list_data_history"

class DataHistory(val context: Context,val history : Constutor_View.GetDataHistory)  : ConstutorPrecenter.DataHistory {
    override fun getHistoryData(id: Int) {
        val preferances = context.getSharedPreferences(HISTORY, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferances.getString(DATALISTHISTORY, test)
        val typeToken = object : TypeToken<ArrayList<Int>>() {}.type
        val listData: ArrayList<Int>? = gson.fromJson(json, typeToken)
        var listDatanew = listData ?: arrayListOf()
        listDatanew.let {
            Log.e("listFavorite_inPresente", listDatanew.toString())
            history.listHistoryData(it, id)
        }
    }

    override fun setHistoryData(list: ArrayList<Int>?) {
        val preferances = context.getSharedPreferences(HISTORY, Context.MODE_PRIVATE)
        val edit = preferances.edit()
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
package com.example.napat.movieapp.history.presenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.history.view.ConstuterViewHistory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


var NEWHISTORY = "File Key History"
var NEWDATALISTHISTORY = "list_data_history"

class DataHistory(val context: Context,
                  private val history : ConstuterViewHistory.GetDataHistory)
                  : ConsPresenterHistory.DataHistory {

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

}
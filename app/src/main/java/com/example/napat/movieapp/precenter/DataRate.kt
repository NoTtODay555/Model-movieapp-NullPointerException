package com.example.napat.movieapp.precenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.model.Rate
import com.example.napat.movieapp.view.Constutor_View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


var DATARATE = "File Key"
var LISTDATARATE = "list_data_rate"

@Suppress("DEPRECATION")
class DataRate(val context: Context, private val showView: Constutor_View.GetDataRate): ConstructorPresenter.DataRate {
    override fun getHistoryData(id: Int) {
        val preferences = context.getSharedPreferences(DATARATE, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferences.getString(LISTDATARATE, test)
        val typeToken = object : TypeToken<ArrayList<Rate>>() {}.type
        val listData: ArrayList<Rate>? = gson.fromJson(json, typeToken)
        Log.e("getView",listData.toString())
        showView.listRateData(listData,id)
    }

    override fun setHistoryData(list: ArrayList<Rate>?,id : Int,ratingpoint : ArrayList<Int>) {
        val preferences = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
        val listViewID = Rate(0, arrayListOf())
        listViewID.id = id
        listViewID.ratingPoint = ratingpoint
        for (i in 0 until (list?.size ?: 0)) {
            if (listViewID.id == list?.get(i)?.id ?: 0) {
                list?.get(i)?.ratingPoint = listViewID.ratingPoint

            }else {
                if(!((list?.size?.minus(1)) != i || listViewID.id == list[i].id)){
                    list.add(listViewID)
                }
            }
        }
        val edit = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        Log.e("listview", json.toString())
        edit.putString(LISTVIEWDATA, json)
        edit.apply()
    }
    override fun findidInArray(list: ArrayList<Rate>, id: Int): Int {
        for (i in 0..list.size - 1) {
            if (list[i].id == id) {
                return i
            }
        }
        return -1
    }

    override fun sumArrayRate(list: ArrayList<Int>) : Int {
        var sum = 0
        for (i in 0 until list.size) sum += list[i]
        return sum
    }

}


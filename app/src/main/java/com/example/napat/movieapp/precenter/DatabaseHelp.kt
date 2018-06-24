@file:Suppress("DEPRECATION")

package com.example.napat.movieapp.precenter

import android.content.Context
import com.google.gson.Gson
import android.util.Log
import com.example.napat.movieapp.model.ListViewData
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList


var DATAVIEW = "File Key"
var VIEWLISE = "view for movie"
var LISTVIEWDATA = "list_data_view"
class WeatherObject {

    var id: Int = 0
    var view : Int = 0

}

class DatabaseHelp(val context: Context) {

    val preferances = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
    fun gettestdata(): ArrayList<ListViewData>? {
        val test = ""
        val gson = Gson()
        val json: String = preferances.getString(LISTVIEWDATA, test)
        var typeToken = object : TypeToken<ArrayList<ListViewData>>() {}.type
        var listData: ArrayList<ListViewData>? = gson.fromJson(json, typeToken)
        return listData
    }


    fun setLogin(id: Int, view: Int, list: ArrayList<ListViewData>) {
        val listidview = ListViewData(0, 0)
        listidview.id = id
        listidview.viewCount = view
        listidview?.let {
            if (list.size == 0) {
                list.add(it)
            }
            for (i in 0..list.size - 1) {
                if (list[i].id == listidview.id) {
                    list[i].viewCount = listidview.viewCount
                }
                if (list[i].id != listidview.id && list.size - 1 == i) {
                    list.add(it)
                }
            }
        }
        val edit = preferances.edit()

        val gson = Gson()
        val json = gson.toJson(list)

        Log.e("listview", json.toString())
        edit.putString(LISTVIEWDATA, json)
        edit.commit()
    }

    fun findidInArray(list: ArrayList<ListViewData>, id: Int): Int {
        for (i in 0..list.size - 1) {
            if (list[i].id == id) {
                return i
            }
        }
        return -1
    }
}


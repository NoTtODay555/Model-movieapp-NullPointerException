@file:Suppress("DEPRECATION")

package com.example.napat.movieapp.precenter

import android.content.Context
import com.google.gson.Gson
import android.util.Log
import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.view.Constutor_View
import com.example.napat.movieapp.view.ShowMovie
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList


var DATAVIEW = "File Key"
var LISTVIEWDATA = "list_data_view"


class DatabaseHelp(val context: Context,val showView: Constutor_View.GetDataView) : ConstutorPrecenter.DataView {

    override fun getViewData(id : Int) {
        val preferances = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferances.getString(LISTVIEWDATA, test)
        val typeToken = object : TypeToken<ArrayList<ListViewData>>() {}.type
        val listData: ArrayList<ListViewData>? = gson.fromJson(json, typeToken)
        Log.e("getView",listData.toString())
        showView.listViewData(listData,id)
    }


    override fun setViewData(id: Int, view: Int, list: ArrayList<ListViewData>?) {
        val preferances = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
        val listidview = ListViewData(0, 0)
        listidview.id = id
        listidview.viewCount = view
        listidview?.let {
            if (list?.size ?: 0 == 0) {
                list?.add(it)
            }
            for (i in 0..(list?.size ?: 0) - 1) {
                if (list?.get(i)?.id   == listidview.id) {
                    list?.get(i)?.viewCount = listidview.viewCount
                }
                else if (list?.get(i)?.id   != listidview.id && (list?.size)?.minus(1)  == i) {
                    list.add(it)
                }
            }
        }
        val edit = preferances.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        Log.e("listview", json.toString())
        edit.putString(LISTVIEWDATA, json)
        edit.apply()
    }

    override fun findidInArray(list: ArrayList<ListViewData>, id: Int): Int {
        for (i in 0..list.size - 1) {
            if (list[i].id == id) {
                return i
            }
        }
        return -1
    }
}


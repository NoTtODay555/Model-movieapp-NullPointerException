@file:Suppress("DEPRECATION")

package com.example.napat.movieapp.precenter

import android.content.Context
import com.google.gson.Gson
import android.util.Log
import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.view.Constutor_View
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList


var DATAVIEW = "File Key"
var LISTVIEWDATA = "list_data_view"


class DatabaseHelp(val context: Context,val showView: Constutor_View.GetDataView) : ConstructorPresenter.DataView {

    override fun getViewData(id : Int) {
        val preference = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preference.getString(LISTVIEWDATA, test)
        val typeToken = object : TypeToken<ArrayList<ListViewData>>() {}.type
        val listData: ArrayList<ListViewData>? = gson.fromJson(json, typeToken)
        Log.e("getView",listData.toString())
        showView.listViewData(listData,id)
    }


    override fun setViewData(id: Int, view: Int, list: ArrayList<ListViewData>?) {
        val preferences = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
        val listViewId = ListViewData(0, 0)
        listViewId.id = id
        listViewId.viewCount = view
        listViewId.let {
            if (list?.size ?: 0 == 0) {
                list?.add(it)
            }
            for (i in 0..(list?.size ?: 0) - 1) {
                if (list?.get(i)?.id   == listViewId.id) {
                    list[i].viewCount = listViewId.viewCount
                }
                else if (list?.get(i)?.id   != listViewId.id && (list?.size)?.minus(1)  == i) {
                    list.add(it)
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

    override fun findIdInArray(list: ArrayList<ListViewData>, id: Int): Int {
        for (i in 0 until list.size) {
            if (list[i].id == id) {
                return i
            }
        }
        return -1
    }
}


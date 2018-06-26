@file:Suppress("DEPRECATION")

package com.example.napat.movieapp.precenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.view.Constutor_View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

var DATAVIEW = "File Key"
var LISTVIEWDATA = "list_data_view"

class DatabaseHelp(val context: Context, val showView: Constutor_View.GetDataView) :
    ConstructorPresenter.DataView {

    override fun getViewData(id: Int) {
        val preference = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preference.getString(LISTVIEWDATA, test)
        val typeToken = object : TypeToken<ArrayList<ListViewData>>() {}.type
        val listData: ArrayList<ListViewData>? = gson.fromJson(json, typeToken)
        Log.e("getView", listData.toString())
        showView.listViewData(listData?.find { it.id == id }, id)
    }

    override fun setViewData(id: Int, view: Int, list: ListViewData?) {
        val preferences = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
        var keptList = getViewData()
        if (keptList?.isEmpty() == true) {
            keptList = ArrayList()
        }
        val filteredList = keptList?.filter { it.id == id }
        when (filteredList?.isEmpty()) {
            true -> {
                keptList?.add(list ?: ListViewData())
            }
            else -> {
                keptList?.forEach {
                    if (id == it.id) {
                        it.viewCount = it.viewCount?.plus(1)
                    }
                }
            }
        }
        val edit = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(keptList)
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

    private fun getViewData(): ArrayList<ListViewData>? {
        val preference = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preference.getString(LISTVIEWDATA, test)
        val typeToken = object : TypeToken<ArrayList<ListViewData>>() {}.type
        return gson.fromJson(json, typeToken)
    }
}


package com.example.napat.movieapp.show.presenter

import android.content.Context
import android.util.Log
import com.example.napat.movieapp.history.presenter.NEWDATALISTHISTORY
import com.example.napat.movieapp.history.presenter.NEWHISTORY
import com.example.napat.movieapp.model.ListDataViweHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SetDataHistory(var context: Context) : ConsPresenterShow.SetDataHis {
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
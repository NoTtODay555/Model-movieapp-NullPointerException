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
    override fun getRateData(id: Int, fl : Float) {
        val preferences = context.getSharedPreferences(DATARATE, Context.MODE_PRIVATE)
        val test = ""
        val gson = Gson()
        val json: String = preferences.getString(LISTDATARATE, test)
        val typeToken = object : TypeToken<ArrayList<Rate>>() {}.type
        val listData: ArrayList<Rate>? = gson.fromJson(json, typeToken)
        Log.e("getView",listData.toString())
        showView.listRateData(listData,id,fl)
    }

    override fun setRateData(ist: ArrayList<Rate>?, id : Int, ratingPoint : ArrayList<Float>) {
        val preferences = context.getSharedPreferences(DATAVIEW, Context.MODE_PRIVATE)
        val listViewID = Rate(0, arrayListOf())
        listViewID.id = id
        listViewID.ratingPoint = ratingPoint
        Log.e("listViewID",listViewID.toString())
        if (ist?.size ?: 0 == 0) {
            val list = arrayListOf<Rate>()
            list.add(listViewID)
            Log.e("list",list.toString())
            ist?.addAll(list)

        }
        for (i in 0 until (ist?.size ?: 0)) {
            if (listViewID.id == ist?.get(i)?.id ?: 0) {
                ist?.get(i)?.ratingPoint?.add(listViewID.ratingPoint[0])
            }else {
                if(!((ist?.size?.minus(1)) != i || listViewID.id == ist[i].id)){
                    ist.add(listViewID)
                }
            }
        }
        val edit = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(ist)
        Log.e("listview", json.toString())
        edit.putString(LISTDATARATE, json)
        edit.apply()
    }
    override fun findidInArray(list: ArrayList<Rate>, id: Int): Int {
        for (i in 0 until list.size) {
            if (list[i].id == id) {
                return i
            }
        }
        return -1
    }

    override fun sumArrayRate(list: ArrayList<Float>) : Float {
        var sum = 0.0F
        for (i in 0 until list.size) sum += list[i]
        return sum
    }

}


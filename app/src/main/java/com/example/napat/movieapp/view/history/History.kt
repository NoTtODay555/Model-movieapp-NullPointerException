package com.example.napat.movieapp.view.history

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.precenter.ConstructorPresenter
import com.example.napat.movieapp.precenter.DataHistory
import com.example.napat.movieapp.view.Constutor_View
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity(), Constutor_View.GetDataHistory {
    val list = arrayListOf<ListDataViweHolder>()
    private val listHistory = arrayListOf<ListDataViweHolder>()
    private val recyclableAdapter = RecycleHistory(listHistory, this)
    override fun listHistoryData(listListViweHolder: ArrayList<ListDataViweHolder>?) {
        listListViweHolder?.let { recyclableAdapter.getList(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val dataFavorite : ConstructorPresenter.DataHistory = DataHistory(this,this)
        dataFavorite.getHistoryData()
        Log.e("listData",list.toString())
        rv_history.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclableAdapter
        }
    }
}

package com.example.napat.movieapp.view.history

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import com.example.napat.movieapp.precenter.DataHistory
import com.example.napat.movieapp.precenter.PrecenterMain
import com.example.napat.movieapp.view.Constutor_View
import com.example.napat.movieapp.view.favorite.RecycleFavorite
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() ,Constutor_View.getApitext, Constutor_View.GetDataHistory {
    private val presenter : ConstutorPrecenter.Main = PrecenterMain(this)
    val list = arrayListOf<MovieDetail>()
    val listHistory = arrayListOf<MovieDetail>()
    val recyclableAdapter = RecycleHistory(listHistory, this)
    override fun showText(a: MovieDetail) {
        list.add(a)
        Log.e("AddList",list.toString())
        recyclableAdapter.getlist(list)
    }

    override fun listActor(actor: ActorDetail) {
    }


    override fun listHistoryData(listFavorite: ArrayList<Int>?, id: Int) {
        for(i in 0 ..(listFavorite?.size?.minus(1) ?:  0)){
            Log.e("id",i.toString())
            listFavorite?.get(i)?.let { presenter.getId(it) }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val dataFavorite : ConstutorPrecenter.DataHistory = DataHistory(this,this)
        dataFavorite.getHistoryData(0)
        Log.e("listData",list.toString())
        rv_history.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclableAdapter
        }
    }
}

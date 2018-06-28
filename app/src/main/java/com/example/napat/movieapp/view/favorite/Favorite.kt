package com.example.napat.movieapp.view.favorite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.precenter.ConstructorPresenter
import com.example.napat.movieapp.precenter.DatabaseFavorite
import com.example.napat.movieapp.precenter.PresenterMain
import com.example.napat.movieapp.view.Constutor_View
import kotlinx.android.synthetic.main.activity_favorite.*

class Favorite : AppCompatActivity(),Constutor_View.GetDataFavorite{
    val list = arrayListOf<ListDataViweHolder>()
    private val recyclableAdapter = RecycleFavorite(list, this)

    override fun listFavoriteData(listFavorite: ArrayList<ListDataViweHolder>?, favorite: Boolean) {
        recyclableAdapter.getList(listFavorite ?: ArrayList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val dataFavorite : ConstructorPresenter.DataFavorite= DatabaseFavorite(this,this)
        dataFavorite.getFavoriteData(0)
        rv_favorite.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclableAdapter
        }
    }
}

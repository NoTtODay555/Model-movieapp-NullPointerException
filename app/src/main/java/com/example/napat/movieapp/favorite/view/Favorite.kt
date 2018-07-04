package com.example.napat.movieapp.favorite.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ListDataViweHolder
import com.example.napat.movieapp.favorite.presenter.GetFavorite
import com.example.napat.movieapp.favorite.presenter.PresenterFavorite
import kotlinx.android.synthetic.main.activity_favorite.*

class Favorite : AppCompatActivity(), ConsFavorite.GetDataFavorite{
    val list = arrayListOf<ListDataViweHolder>()
    private val recyclableAdapter = RecycleFavorite(list, this)

    override fun listFavoriteData(listFavorite: ArrayList<ListDataViweHolder>?, favorite: Boolean) {
        recyclableAdapter.getList(listFavorite ?: ArrayList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val dataFavorite : PresenterFavorite.DataFavorite= GetFavorite(this, this)
        dataFavorite.getFavoriteData(0)
        rv_favorite.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclableAdapter
        }
    }
}

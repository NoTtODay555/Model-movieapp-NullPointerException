package com.example.napat.movieapp.view.favorite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import com.example.napat.movieapp.precenter.DatabaseFavorrite
import com.example.napat.movieapp.precenter.PrecenterMain
import com.example.napat.movieapp.view.Constutor_View
import com.example.napat.movieapp.view.main.RecycleviewAdapter
import kotlinx.android.synthetic.main.activity_favorite.*

class Favorite : AppCompatActivity(),Constutor_View.GetDataFavorite,
        Constutor_View.getApitext {
    private val presenter : ConstutorPrecenter.Main = PrecenterMain(this)
    val list = arrayListOf<MovieDetail>()
    val recyclableAdapter = RecycleFavorite(list, this)
    override fun showText(a: MovieDetail) {
        list.add(a)
        Log.e("AddList",list.toString())
        recyclableAdapter.getlist(list)
    }

    override fun listActor(actor: ActorDetail) {
    }

    override fun listFavoriteData(listFavorite: ArrayList<Int>?, id: Int, count: Int) {

        for(i in 0 ..(listFavorite?.size?.minus(1) ?:  0)){
            Log.e("id",i.toString())
            listFavorite?.get(i)?.let { presenter.getId(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val dataFavorite = DatabaseFavorrite(this,this)
        dataFavorite.getFavoriteData(0,0)
        Log.e("listData",list.toString())
        rv_favorite.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclableAdapter
        }
    }
}

package com.example.napat.movieapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.*
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import com.example.napat.movieapp.precenter.DatabaseHelp
import com.example.napat.movieapp.precenter.PrecenterMain
import com.example.napat.movieapp.precenter.PresenterShow
import kotlinx.android.synthetic.main.activity_show_movie.*

class ListViewDataObject{
    var id : Int = 0
    var view : Int = 0
}

class ShowMovie : AppCompatActivity()
    ,Constutor_View.getApitext ,
        Constutor_View.ShowTextFaverite{
    override fun showFaveritetext(text: String, count: Int) {
        bt_favorite.text = text
        count1 = count
    }

    var a: String = ""
    var num1 = 1
    var list = arrayListOf<ListViewData>()
    var count1 = 1
    var chackFavoriteBT = PresenterShow(this)
    override fun listActor(actor: ActorDetail) {
        Log.e("test", actor.toString())
        for (i in 0..actor.cast.size - 1) {
            a = a + actor.cast[i].character + ","
        }
        tv_actor.text = a
    }

    private val presenter: ConstutorPrecenter.Main = PrecenterMain(this)
    private var popularMovie: MovieDetail? = null
    override fun showText(a: MovieDetail) {
        popularMovie = a
        Log.e("test", a.backdrop_path)
        Glide.with(this).load(BaseUrl.baseUrlImageMovie + (popularMovie?.backdrop_path)).into(im_showmovie)
        tv_titlename.text = a.title

        tv_overviewename.text = a.overview
        ratingBar.rating = (a.vote_average / 2).toFloat()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movie)
        val id = intent.extras.getInt("IdMovieDetail")
        Log.e("id", id.toString())
        presenter.getIdActor(id)
        presenter.getId(id)
        val my = DatabaseHelp(this)

        list = my.gettestdata() ?: arrayListOf()
        Log.e("id", list.toString())

        var numlist = my.findidInArray(list,id)
        if(numlist == -1) {
            numlist = list.size
            num1 = 1
        }
        else num1 = list[numlist].viewCount + 1
        chackFavoriteBT.ChackButton(count1)


        my.setLogin(id, num1,list)
        testxx.text = list?.get(numlist)?.viewCount.toString()
        bt_favorite.setOnClickListener {
            count1+1
            chackFavoriteBT.ChackButton(count1)
        }
    }
}

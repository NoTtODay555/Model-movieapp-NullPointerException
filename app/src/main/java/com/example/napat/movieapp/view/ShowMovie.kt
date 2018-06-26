package com.example.napat.movieapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.*
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.precenter.*
import kotlinx.android.synthetic.main.activity_show_movie.*

class ShowMovie : AppCompatActivity()
    ,Constutor_View.getApitext ,
        Constutor_View.ShowTextFaverite,
        Constutor_View.GetDataView,
        Constutor_View.GetDataFavorite,
        Constutor_View.GetDataHistory,
        Constutor_View.GetDataRate{


    private val dataView :ConstructorPresenter.DataView = DatabaseHelp(this,this)
    private val dataFavorite = DatabaseFavorite(this,this)
    private val dataHistory = DataHistory(this,this)
    private val dataRate : ConstructorPresenter.DataRate = DataRate(this,this)
    var a: String = ""
    var num1 = 1
    var list = arrayListOf<ListViewData>()
    var count1 = 0
    private var checkFavoriteBT = PresenterShow(this)
    private val presenter : ConstructorPresenter.Main = PresenterMain(this)
    private var popularMovie : MovieDetail? = null

    override fun listRateData(listRate: ArrayList<Rate>?, id: Int, fl : Float) {
        val i = listRate?.let { dataRate.findidInArray(it,id) }
        val sum = listRate?.let{ i?.let { it1 -> it[it1].ratingPoint }?.let { it2 -> dataRate.sumArrayRate(it2)}}
        Log.e("sum",sum.toString())
    }

    override fun listFavoriteData(listFavorite: ArrayList<Int>?, id: Int,count: Int) {
        count1 = dataFavorite.findFavoriteInArray(listFavorite,id)
        if ( count == 3){
            listFavorite?.add(id)
            dataFavorite.setFavoriteData(listFavorite)
            Log.e("add",listFavorite.toString())
            checkFavoriteBT.checkButton(2,id)
        }
        if (count == 4){
            listFavorite?.remove(id)
            Log.e("remove",listFavorite.toString())
            dataFavorite.setFavoriteData(listFavorite)
            checkFavoriteBT.checkButton(1,id)
        }
    }

    override fun listHistoryData(listHistory: ArrayList<Int>?, id: Int) {
        Log.e("listHistory",listHistory.toString())
        when {
            dataHistory.findIdinArray(listHistory,id) -> {
                listHistory?.remove(id)
                listHistory?.add(id)
            }
            listHistory?.size ?: 0 <= 10 -> listHistory?.add(id)
            else -> {
                listHistory?.removeAt(10)
                listHistory?.add(id)
            }
        }
        dataHistory.setHistoryData(listHistory)
    }

    override fun listViewData(listView: ArrayList<ListViewData>?, id: Int) {
        list = listView ?: arrayListOf()
        Log.e("ListView",list.toString())
        var numlist = dataView.findIdInArray(list,id)
        Log.e("id",numlist.toString())
        if(numlist == -1) {
            numlist = list.size
            num1 = 0
            dataView.setViewData(id, num1,list)
            dataView.getViewData(id)
        } else {
            num1 = list[numlist].viewCount + 1
        }
        testxx.text = list[numlist].viewCount.toString()
        dataView.setViewData(id, num1,list)
    }

    override fun showFaveritetext(text: String, count: Int,id : Int) {
        bt_favorite.text = text
        count1 = count
    }

    override fun listActor(actor: ActorDetail) {
        for (i in 0 until actor.cast.size) {
            a = a + actor.cast[i].character + ","
        }
        tv_actor.text = a
    }

    override fun showText(a: MovieDetail) {
        popularMovie = a
        Glide.with(this).load(BaseUrl.baseUrlImageMovie + (popularMovie?.backdrop_path)).into(im_showmovie)
        tv_titlename.text = a.title
        tv_overviewename.text = a.overview
        ratingBar.rating = a.vote_average.toFloat()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movie)
        val id = intent.extras.getInt("IdMovieDetail")
        presenter.getIdActor(id)
        presenter.getId(id)
        dataView.getViewData(id)
        dataHistory.getHistoryData(id)
        dataFavorite.getFavoriteData(id,count1)
        checkFavoriteBT.checkButton(count1,id)
        bt_favorite.setOnClickListener {
            checkFavoriteBT.checkButton(count1,id)
            if(count1 == 1){
                dataFavorite.getFavoriteData(id,3)
            }else{
                dataFavorite.getFavoriteData(id,4)
            }
        }
        ratingBar.setOnRatingBarChangeListener { ratingBar, fl, boo ->
                dataRate.getHistoryData(id,fl)
                ratingBar.setIsIndicator(boo)
        }
    }
}

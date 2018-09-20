package com.example.napat.movieapp.show.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.*
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.model.network.Detail
import com.example.napat.movieapp.show.presenter.*
import kotlinx.android.synthetic.main.activity_show_movie.*

@Suppress("DEPRECATION")
class ShowMovie : AppCompatActivity()
    , ConstutorViewShow.ShowTextFavorite,
        ConstutorViewShow.GetDataView,
        ConstutorViewShow.GetDataFavorite,
        ConstutorViewShow.GetDataRate,
        ConstutorViewShow.GetDetail {
    private var load : ProgressDialog? = null
    override fun listDetail(listDetail: Detail) {
        load?.dismiss()
        popularMovie = listDetail.movieDetail
        Glide.with(this).load(BaseUrl.baseUrlImageMovie + (listDetail.movieDetail.backdrop_path)).into(im_showmovie)
        tv_titlename.text = listDetail.movieDetail.title
        tv_overviewename.text = listDetail.movieDetail.overview
        setHistory.setHistoryData(listDetail.movieDetail.id,listDetail.movieDetail.title,listDetail.movieDetail.backdrop_path)
        for (i in 0 until listDetail.actorDetail.cast.size) {
            a = a + listDetail.actorDetail.cast[i].character + ","
        }
        tv_actor.text = a
    }

    private val dataView: ConsPresenterShow.DataView = DatabaseHelp(this, this)
    private val dataFavorite = SetFavorite(this,this)
    private val showDetail : ConsPresenterShow.DetailShow = PresenterDetailShow(this)
    private val setHistory = SetDataHistory(this)
    private val dataRate: ConsPresenterShow.DataRate = DataRate(this, this)
    var a: String = ""
    private var boo = false
    var list = arrayListOf<ListViewData>()

    private var checkFavoriteBT = PresenterShow(this)
    private var popularMovie: MovieDetail? = null

    override fun listRateData(listRate: ArrayList<Rate>?, id: Int, fl: Float) {
        val arrayFloat = arrayListOf<Float>()
        listRate?.let { dataRate.findidInArray(it, id) }
        Log.e("sum", fl.toString())
        arrayFloat.add(fl)
        var sumInt = 0.0F
        val filteredList = listRate?.filter { it.id == id } ?: listOf()
        dataRate.setRateData(listRate ?: arrayListOf(), id, arrayFloat)
        when (filteredList.isEmpty()) {
            true -> {
                dataRate.setRateData(listRate,id,arrayFloat)
                sumInt = fl
            }
            else -> {
                dataRate.setRateData(listRate,id,arrayFloat)
                filteredList[0].ratingPoint.forEach {
                    sumInt += it
                }
            }
        }
        ratingBar.rating = sumInt
    }

    override fun listFavoriteData(listFavorite: ArrayList<ListDataViweHolder>?, favorite: Boolean) {
        checkFavoriteBT.checkButton(favorite)
        boo = favorite
    }

    override fun listViewData(listView: ListViewData?, id: Int) {
        Log.e("listViewData",listView.toString())
        val tempListView: ListViewData? = when (listView == null) {
            true -> ListViewData()
            else -> listView
        }
        when (tempListView?.id == null) {
            true -> {
                testxx.text = "1"
            }
            else -> {
                testxx.text = listView?.viewCount.toString()
            }
        }
        tempListView?.viewCount = testxx.text.toString().toInt().plus(1)
        tempListView?.id = id
        Log.e("tempListView",tempListView.toString())
        dataView.setViewData(id, listView?.viewCount?.plus(1) ?: 0, tempListView)
    }

    override fun showFavoriteText(text: String) {
        bt_favorite.text = text
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movie)
        val id = intent.extras.getInt("IdMovieDetail")
        dataView.getViewData(id)
        dataFavorite.getFavoriteData(id)
        showDetail.getDataDetail(id)
        val dialogGG= ProgressDialog.show(this, "",
                "Loading. Please wait...", true,false)
        load = dialogGG
        ratingBar.rating = 0.0f
        ratingBar.setIsIndicator(false)
        bt_favorite.setOnClickListener {
            when(boo){
                true -> {
                    boo = false
                    popularMovie?.backdrop_path?.let {
                        it1 -> popularMovie?.title?.let {
                        it2 -> popularMovie?.id?.let {
                        it3 -> dataFavorite.setFavoriteData(it3, it2, it1,boo)}}}
                    dataFavorite.getFavoriteData(id)
                }
                false -> {
                    boo = true
                    popularMovie?.backdrop_path?.let {
                        it1 -> popularMovie?.title?.let {
                        it2 -> popularMovie?.id?.let {
                        it3 -> dataFavorite.setFavoriteData(it3, it2, it1,boo)} } }
                    dataFavorite.getFavoriteData(id)
                }
            }
        }

        ratingBar.setOnRatingBarChangeListener { ratingBar, fl, bo ->
            Log.e("Boo",bo.toString())
            ratingBar.setIsIndicator(true)
            if(bo) dataRate.getRateData(id,fl)
        }
    }

}

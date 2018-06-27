package com.example.napat.movieapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.ListViewData
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.model.Rate
import com.example.napat.movieapp.model.network.BaseUrl
import com.example.napat.movieapp.precenter.ConstructorPresenter
import com.example.napat.movieapp.precenter.DataHistory
import com.example.napat.movieapp.precenter.DataRate
import com.example.napat.movieapp.precenter.DatabaseFavorite
import com.example.napat.movieapp.precenter.DatabaseHelp
import com.example.napat.movieapp.precenter.PresenterMain
import com.example.napat.movieapp.precenter.PresenterShow
import kotlinx.android.synthetic.main.activity_show_movie.*

class ShowMovie : AppCompatActivity()
    , Constutor_View.getApitext,
    Constutor_View.ShowTextFaverite,
    Constutor_View.GetDataView,
    Constutor_View.GetDataFavorite,
    Constutor_View.GetDataHistory,
    Constutor_View.GetDataRate {

    private val dataView: ConstructorPresenter.DataView = DatabaseHelp(this, this)
    private val dataFavorite = DatabaseFavorite(this, this)
    private val dataHistory = DataHistory(this, this)
    private val dataRate: ConstructorPresenter.DataRate = DataRate(this, this)
    var a: String = ""
    var num1 = 1
    var list = arrayListOf<ListViewData>()
    var count1 = 0
    private var checkFavoriteBT = PresenterShow(this)
    private val presenter: ConstructorPresenter.Main = PresenterMain(this)
    private var popularMovie: MovieDetail? = null

    override fun listRateData(listRate: ArrayList<Rate>?, id: Int, fl: Float) {
        val arrayFloat = arrayListOf<Float>()
        val i = listRate?.let { dataRate.findidInArray(it, id) }
        Log.e("sum", fl.toString())
        arrayFloat.add(fl)
        dataRate.setRateData(listRate ?: arrayListOf(), id, arrayFloat)
        val sum = listRate?.let {
            i?.let { it1 -> it[it1].ratingPoint }?.let { it2 -> dataRate.sumArrayRate(it2) }
        }
        ratingBar.rating = sum ?: 0.0F
    }

    override fun listFavoriteData(listFavorite: ArrayList<Int>?, id: Int, count: Int) {
        count1 = dataFavorite.findFavoriteInArray(listFavorite, id)
        if (count == 3) {
            listFavorite?.add(id)
            dataFavorite.setFavoriteData(listFavorite)
            Log.e("add", listFavorite.toString())
            checkFavoriteBT.checkButton(2, id)
        }
        if (count == 4) {
            listFavorite?.remove(id)
            Log.e("remove", listFavorite.toString())
            dataFavorite.setFavoriteData(listFavorite)
            checkFavoriteBT.checkButton(1, id)
        }
    }

    override fun listHistoryData(listHistory: ArrayList<Int>?, id: Int) {
        Log.e("listHistory", listHistory.toString())
        when {
            dataHistory.findIdinArray(listHistory, id) -> {
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

    override fun showFaveritetext(text: String, count: Int, id: Int) {
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
        Glide.with(this).load(BaseUrl.baseUrlImageMovie + (popularMovie?.backdrop_path))
            .into(im_showmovie)
        tv_titlename.text = a.title
        tv_overviewename.text = a.overview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movie)
        val id = intent.extras.getInt("IdMovieDetail")
        presenter.getIdActor(id)
        presenter.getId(id)
        dataView.getViewData(id)
        dataHistory.getHistoryData(id)
        dataFavorite.getFavoriteData(id, count1)
        checkFavoriteBT.checkButton(count1, id)
        ratingBar.rating = 0.0f
        ratingBar.setIsIndicator(false)
        bt_favorite.setOnClickListener {
            checkFavoriteBT.checkButton(count1, id)
            if (count1 == 1) {
                dataFavorite.getFavoriteData(id, 3)
            } else {
                dataFavorite.getFavoriteData(id, 4)
            }
        }
        ratingBar.setOnRatingBarChangeListener { ratingBar, fl, boo ->
            Log.e("Boo", boo.toString())
            ratingBar.setIsIndicator(true)
            dataRate.getRateData(id, fl)
        }
    }
}

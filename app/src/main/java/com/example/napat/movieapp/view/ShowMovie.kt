package com.example.napat.movieapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.*
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
    Constutor_View.GetDataRate,
    Constutor_View.GetDataHistory{
    override fun listHistoryData(listListViweHolder: ArrayList<ListDataViweHolder>?) {}
    private val dataView: ConstructorPresenter.DataView = DatabaseHelp(this, this)
    private val dataFavorite = DatabaseFavorite(this, this)
    private val dataHistory = DataHistory(this,this)
    private val dataRate: ConstructorPresenter.DataRate = DataRate(this, this)
    var a: String = ""
    var boo = false
    var list = arrayListOf<ListViewData>()

    private var checkFavoriteBT = PresenterShow(this)
    private val presenter: ConstructorPresenter.Main = PresenterMain(this)
    private var popularMovie: MovieDetail? = null

    override fun listRateData(listRate: ArrayList<Rate>?, id: Int, fl: Float) {
        val arrayFloat = arrayListOf<Float>()
        val i = listRate?.let { dataRate.findidInArray(it, id) }
        Log.e("sum", fl.toString())
        arrayFloat.add(fl)
        var sumint = 0.0F
        val filteredList = listRate?.filter { it.id == id } ?: listOf()
        dataRate.setRateData(listRate ?: arrayListOf(), id, arrayFloat)
        when (filteredList.isEmpty()) {
            true -> {
                dataRate.setRateData(listRate,id,arrayFloat)
                sumint = fl
            }
            else -> {
                dataRate.setRateData(listRate,id,arrayFloat)
                filteredList[0].ratingPoint.forEach {
                    sumint += it
                }
            }
        }
        ratingBar.rating = sumint
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

    override fun showFaveritetext(text: String) {
        bt_favorite.text = text
    }

    override fun listActor(actor: ActorDetail) {
        for (i in 0 until actor.cast.size) {
            a = a + actor.cast[i].character + ","
        }
        tv_actor.text = a
    }

    override fun showText(a: MovieDetail){
        popularMovie = a
        Glide.with(this).load(BaseUrl.baseUrlImageMovie + (a.backdrop_path)).into(im_showmovie)
        tv_titlename.text = a.title
        tv_overviewename.text = a.overview
        dataHistory.setHistoryData(a.id,a.title,a.backdrop_path)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movie)
        val id = intent.extras.getInt("IdMovieDetail")
        presenter.getIdActor(id)
        presenter.getId(id)
        dataView.getViewData(id)
        dataFavorite.getFavoriteData(id)
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
            Log.e("Boo",boo.toString())
            ratingBar.setIsIndicator(true)
            if(bo) dataRate.getRateData(id,fl)
        }

    }

}

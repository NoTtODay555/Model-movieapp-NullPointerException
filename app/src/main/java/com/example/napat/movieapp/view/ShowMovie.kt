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
        Constutor_View.GetDataHistory{

    val dataView :ConstutorPrecenter.DataView = DatabaseHelp(this,this)
    val dataFavorite = DatabaseFavorrite(this,this)
    val dataHistory = DataHistory(this,this)
    var a: String = ""
    var num1 = 1
    var list = arrayListOf<ListViewData>()
    var count1 = 0
    var chackFavoriteBT = PresenterShow(this)

    private val presenter : ConstutorPrecenter.Main = PrecenterMain(this)
    private var popularMovie : MovieDetail? = null

    override fun listFavoriteData(listFavorite: ArrayList<Int>?, id: Int,count: Int) {
        count1 = dataFavorite.findFavoriteInArray(listFavorite,id)
        if ( count == 3){
            listFavorite?.add(id)
            dataFavorite.setFavoriteData(listFavorite)
            Log.e("add",listFavorite.toString())
            chackFavoriteBT.ChackButton(2,id)
        }
        if (count == 4){
            listFavorite?.remove(id)
            Log.e("remove",listFavorite.toString())
            dataFavorite.setFavoriteData(listFavorite)
            chackFavoriteBT.ChackButton(1,id)
        }
    }

    override fun listHistoryData(listHistory: ArrayList<Int>?, id: Int) {
        Log.e("listHistory",listHistory.toString())
        if(dataHistory.findIdinArray(listHistory,id) == true) {
            listHistory?.remove(id)
            listHistory?.add(id)
        }else if(listHistory?.size ?: 0 <= 10 ){
            listHistory?.add(id)
        }else{
            listHistory?.removeAt(10)
            listHistory?.add(id)
        }
        dataHistory.setHistoryData(listHistory)

    }



    override fun listViewData(listView: ArrayList<ListViewData>?, id: Int) {
        list = listView ?: arrayListOf()
        Log.e("ListView",list.toString())
        var numlist = dataView.findidInArray(list,id)
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
        for (i in 0..actor.cast.size - 1) {
            a = a + actor.cast[i].character + ","
        }
        tv_actor.text = a
    }

    override fun showText(a: MovieDetail) {
        popularMovie = a
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
        dataView.getViewData(id)
        dataHistory.getHistoryData(id)

        Log.e("count",count1.toString())
        dataFavorite.getFavoriteData(id,count1)
        chackFavoriteBT.ChackButton(count1,id)
        bt_favorite.setOnClickListener {
            Log.e("test",count1.toString())
            chackFavoriteBT.ChackButton(count1,id)
            if(count1 == 1){
                Log.e("getFavoriteData1",count1.toString())
                dataFavorite.getFavoriteData(id,3)
            }else{
                Log.e("getFavoriteData2",count1.toString())
                dataFavorite.getFavoriteData(id,4)
            }
        }
    }
}

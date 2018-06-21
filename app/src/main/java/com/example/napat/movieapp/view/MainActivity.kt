package com.example.napat.movieapp.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.PopularMovie
import com.example.napat.movieapp.model.Result
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import com.example.napat.movieapp.precenter.PrecenterMain
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
                    Constutor_View.getApiMovieDetail{
//    override fun onLoadMore() {
//        num += 1
//        presenterpopular.pullpage(num)
//    }
//
//    var num : Int = 0
//    private var popularMovie : ArrayList<Result> = arrayListOf()
//    override fun getPageList(data: PopularMovie) {
//        popularMovie.addAll(data.results)
//        recyclableAdapter.getlist(popularMovie)
//    }

    override fun showText(a: String) {
        Log.e("button","Ok")
        test.text = a
    }
//    private val recyclableAdapter : RecycleviewAdapter = RecycleviewAdapter(this,popularMovie)
    private val presenter : ConstutorPrecenter.Main = PrecenterMain(this)
//    val presenterpopular: ConstutorPrecenter.RecycleviewMovie = PresenterRecycleView(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        num += 1
//        presenterpopular.pullpage(num)
        bt_search.setOnClickListener {
            Log.e("button","Ok")
            presenter.getId(et_search.text.toString().toInt())
        }
        val adapter = ViewPagerAdapter(supportFragmentManager,this)
        val pager = findViewById<View>(R.id.vp_image) as ViewPager
        pager.adapter = adapter
//       rv_listMovie?.apply {
//                layoutManager = LinearLayoutManager(this@MainActivity)
//                adapter = recyclableAdapter
//       }
//        recyclableAdapter.setOnLoadMoreListener(this)
    }

    }


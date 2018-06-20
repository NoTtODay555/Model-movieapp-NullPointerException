package com.example.napat.movieapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.napat.movieapp.R
import com.example.napat.movieapp.precenter.ConstutorPrecenter
import com.example.napat.movieapp.precenter.Precenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

//    override fun showText(a: String) {
//        Log.e("button","Ok")
////        test.text = a
//    }
//    private val recyclableAdapter : RecycleviewAdapter = RecycleviewAdapter(this)
//    private val presenter : ConstutorPrecenter.Main = Precenter(this,recyclableAdapter)
//    val presenterpopular: ConstutorPrecenter.RecycleviewMovie = Precenter(this,recyclableAdapter)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        bt_search.setOnClickListener {
//            Log.e("button","Ok")
////            presenter.getId(et_search.text.toString().toInt())
//        }
//        rv_listMovie.apply {
////            adapter = recyclableAdapter
////            presenterpopular.pullMovielist()
//        }

    }
}

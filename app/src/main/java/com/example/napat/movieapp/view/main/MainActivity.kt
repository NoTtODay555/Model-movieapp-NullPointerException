package com.example.napat.movieapp.view.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import com.example.napat.movieapp.R
import com.example.napat.movieapp.precenter.HISTORY
import com.example.napat.movieapp.view.favorite.Favorite
import com.example.napat.movieapp.view.history.History
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bt_search.setOnClickListener {
            Log.e("button","Ok")
            val adapter = ViewPagerAdapter(supportFragmentManager, this,et_search.text.toString())
            val pager = findViewById<View>(R.id.vp_image) as ViewPager
            pager.adapter = adapter
        }
        val adapter = ViewPagerAdapter(supportFragmentManager, this,"")
        val pager = findViewById<View>(R.id.vp_image) as ViewPager
        pager.adapter = adapter
        tabs_main.setupWithViewPager(vp_image)
        bt_favorite.setOnClickListener {
            startActivity(Intent(this, Favorite:: class.java))
        }
        bt_history.setOnClickListener {
            startActivity(Intent(this, History:: class.java))
        }

    }

    }


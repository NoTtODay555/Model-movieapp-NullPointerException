package com.example.napat.movieapp.view

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log


class ViewPagerAdapter(fm : FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        if (position == 1) {
            Log.e("getItem", "OK")
            return AllFragmant()
        } else return AllFragmant()

    }

    override fun getCount(): Int {
        return 2
    }
}


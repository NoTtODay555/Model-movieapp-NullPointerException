package com.example.napat.movieapp.view.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log

class ViewPagerAdapter(fm: FragmentManager, private val context: Context, val word: String) :
    FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            1 -> Fragments.newInstance(1, "", context)
            2 -> Fragments.newInstance(2, "", context)
            else -> {
                Log.e("Run", word)
                Fragments.newInstance(3, word, context)
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            1 -> "Popular Movie"
            2 -> "Toprate Movie"
            else -> "Search"
        }
    }
}


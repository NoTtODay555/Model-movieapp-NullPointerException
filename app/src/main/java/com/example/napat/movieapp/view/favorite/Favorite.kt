package com.example.napat.movieapp.view.favorite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.napat.movieapp.R
import com.example.napat.movieapp.model.ActorDetail
import com.example.napat.movieapp.model.MovieDetail
import com.example.napat.movieapp.view.Constutor_View

class Favorite : AppCompatActivity(), Constutor_View.CheckData ,Constutor_View.getApitext{
    override fun listActor(actor: ActorDetail) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getActor() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showText(a: MovieDetail) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getId(favoriteid: ArrayList<Int>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }
}

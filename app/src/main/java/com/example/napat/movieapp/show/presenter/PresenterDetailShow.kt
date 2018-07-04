package com.example.napat.movieapp.show.presenter

import com.example.napat.movieapp.show.conteck.InterActorShow
import com.example.napat.movieapp.model.network.Detail
import com.example.napat.movieapp.show.conteck.ConsInterActShow
import com.example.napat.movieapp.show.view.ConstutorViewShow

class PresenterDetailShow(val view : ConstutorViewShow.GetDetail) : ConsPresenterShow.DetailShow {
    var interActor : ConsInterActShow.GetApiDetail = InterActorShow(this)
    override fun setDataDetail(listDetail: Detail) {
        view.listDetail(listDetail)
    }

    override fun getDataDetail(id: Int) {
        interActor.getDetail(id)
    }
}
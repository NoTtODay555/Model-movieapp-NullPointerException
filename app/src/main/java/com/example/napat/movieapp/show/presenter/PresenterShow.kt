package com.example.napat.movieapp.show.presenter

import com.example.napat.movieapp.show.view.ConstutorViewShow

class PresenterShow (var view : ConstutorViewShow.ShowTextFavorite) : ConsPresenterShow.CheckFavoriteBT {
    override fun checkButton(boo : Boolean) {
        when(boo){
            false ->{
                view.showFavoriteText("Favorite")
            }
            true -> {
                view.showFavoriteText("add in binary")
            }
        }
    }
}
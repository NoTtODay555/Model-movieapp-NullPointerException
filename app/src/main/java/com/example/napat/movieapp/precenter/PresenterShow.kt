package com.example.napat.movieapp.precenter

import com.example.napat.movieapp.view.Constutor_View

class PresenterShow (var view : Constutor_View.ShowTextFaverite) : ConstructorPresenter.CheckFavoriteBT {
    override fun checkButton(boo : Boolean) {
        when(boo){
            false ->{
                view.showFaveritetext("Favorite")
            }
            true -> {
                view.showFaveritetext("add in binary")
            }
        }
    }
}
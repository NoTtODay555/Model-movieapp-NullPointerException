package com.example.napat.movieapp.precenter

import com.example.napat.movieapp.view.Constutor_View

class PresenterShow (var view : Constutor_View.ShowTextFaverite) : ConstructorPresenter.CheckFavoriteBT {
    override fun checkButton(count: Int, id : Int) {
        when(count){
            1 ->{
                var num = 2
                view.showFaveritetext("Favorite",num,id)
            }
            else -> {
                var num2 = 1
                view.showFaveritetext("add in binary",num2,id)
            }
        }
    }
}
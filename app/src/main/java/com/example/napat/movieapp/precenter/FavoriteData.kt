package com.example.napat.movieapp.precenter

class FavoriteData : ConstutorPrecenter.SaveData , ConstutorPrecenter.DeleteData{
    var listdata : ArrayList<Int> = arrayListOf()
    override fun removeData(Id: Int) {
        listdata.remove(Id)
    }


    override fun addData(id: Int) {
        listdata.add(id)
    }

}
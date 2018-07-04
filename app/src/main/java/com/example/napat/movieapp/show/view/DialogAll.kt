@file:Suppress("DEPRECATION")

package com.example.napat.movieapp.show.view

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.widget.Toast

class DialogAll{


    fun showpopular(context:Context): AlertDialog.Builder {
        var count = 0
        val builder  = AlertDialog.Builder(context)
        builder.setMessage("ย้าาาาา")
        builder.setCancelable(false)
        builder.setNeutralButton("จะเปิดหนังเเล้วนะ") { dialog, p1 ->
            count++
            when(count){
                1 -> {
                    builder.setMessage("เปิดดดจริง ๆ หรออ")
                    builder.show()
                }
                2 -> {
                    builder.setMessage("เเน่ใจนะจะเปิด")
                    builder.show()
                }
                3->{
                    Toast.makeText(context,"อย่าาปิดดดดขอละ", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return builder
    }
    fun showLoading(context: Context): ProgressDialog? {
        val dialogGG= ProgressDialog.show(context, "",
                "Loading. Please wait...", true,false)
        return dialogGG
    }

     fun dismissLoading(load: ProgressDialog?) {
        load?.dismiss()
    }
}
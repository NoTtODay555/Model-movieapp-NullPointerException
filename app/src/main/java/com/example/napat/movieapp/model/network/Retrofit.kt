package com.example.napat.movieapp.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retrofit {
    fun retrofit(baseUrl : String) : Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        }


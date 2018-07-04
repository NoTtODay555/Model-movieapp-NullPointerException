package com.example.napat.movieapp.model


data class ActorDetail(
    val id: Int,
    val cast: List<Cast>
)

data class Cast(
    val cast_id: Int,
    val character: String,
    val id: Int,
    val name: String
)

package com.example.napat.movieapp.history.view

import com.example.napat.movieapp.model.ListDataViweHolder

interface ConstuterViewHistory {
    interface GetDataHistory{
        fun listHistoryData(listListViweHolder : ArrayList<ListDataViweHolder>?)
    }
}
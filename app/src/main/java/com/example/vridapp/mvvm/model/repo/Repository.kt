package com.example.vridapp.mvvm.model.repo

import com.example.vridapp.mvvm.model.api.RetrofitServices

class Repository( private val retrofitServices: RetrofitServices)  {
    suspend fun getAllData( perPage : Int, pagenumber : Int) = retrofitServices.getallData( perPage, pagenumber)
}
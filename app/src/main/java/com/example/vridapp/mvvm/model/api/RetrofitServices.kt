package com.example.vridapp.mvvm.model.api

import com.example.vridapp.mvvm.model.dataresponse.VridResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("posts")
    suspend fun getallData(
        @Query("per_page")
        blogCnt : Int = 10,
        @Query("page")
        pagenumber : Int = 1 ,
    ) : Response<VridResponse>

}
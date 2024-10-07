package com.example.vridapp.mvvm.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    companion object{
        private var retrofitServices : RetrofitServices ? = null
        fun getInstance() : RetrofitServices{
            if ( retrofitServices == null )
            {
                retrofitServices = Retrofit.Builder()
                    .baseUrl("https://blog.vrid.in/wp-json/wp/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitServices::class.java)
            }
            return retrofitServices!!
        }
    }

}
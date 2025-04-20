package com.example.composenewsapp.data.remote.dto

import com.example.e_pharmacycompose.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("everything")
    suspend fun getNews(
        @Query("page") page:Int,
        @Query("sources") sources:String,
        @Query("apiKey") apiKey:String = API_KEY
    ):NewResponse

}
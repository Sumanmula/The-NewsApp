package com.example.newsapplication.network

import com.example.newsapplication.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getNewsFromServer(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "38eec33c66ec45f0b6ad9f5ea7628d0a"
    ): Response<NewsModel>

}
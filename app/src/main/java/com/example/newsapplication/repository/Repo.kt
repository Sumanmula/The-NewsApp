package com.example.newsapplication.repository

import com.example.newsapplication.model.NewsModel
import com.example.newsapplication.network.ApiProvider
import retrofit2.Response

class Repo() {
    suspend fun newProvider() : Response<NewsModel> {
        return ApiProvider.provideApi().getNewsFromServer()
    }
}
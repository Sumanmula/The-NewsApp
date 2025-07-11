package com.example.newsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.model.NewsModel
import com.example.newsapplication.repository.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel() : ViewModel() {

    private val _newsState = MutableStateFlow<Result<NewsModel>>(Result.Loading())
    val newsState = _newsState.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            _newsState.value = Result.Loading()
            try {
                val response = Repo().newProvider()

                kotlinx.coroutines.delay(2000)

                if (response.isSuccessful && response.body() != null) {
                    _newsState.value = Result.Success(response.body())
                } else {
                    _newsState.value = Result.Error("Something went wrong. Please try again.")
                }
            } catch (e: Exception) {
                _newsState.value = Result.Error("No internet connection or server error.")
            }
        }
    }

    fun retry() {
        fetchNews()
    }
}
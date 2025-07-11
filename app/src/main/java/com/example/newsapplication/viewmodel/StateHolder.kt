package com.example.newsapplication.viewmodel

sealed class Result<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T?) : Result<T>(data = data)
    class Loading<T>(message: String? = null) : Result<T>(message = message)
    class Error<T>(message: String?) : Result<T>(message = message)

}
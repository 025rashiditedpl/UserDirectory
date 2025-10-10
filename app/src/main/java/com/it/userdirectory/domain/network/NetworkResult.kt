package com.it.userdirectory.domain.network

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error<out T>(val message: String, val data: T? = null) : NetworkResult<T>()
    data class Loading<out T>(val data: T? = null) : NetworkResult<T>()
}
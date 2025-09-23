package com.it.userdirectory.domain.network

sealed class NetworkResult<out T>(val status: Status, val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : NetworkResult<T>(status = Status.LOADING, data = data,message = null)
    class Success<T>(data: T) : NetworkResult<T>(status = Status.SUCCESS, data = data,message = null)
    class Error<T>(data: T? = null, message: String) : NetworkResult<T>(status = Status.ERROR, data = data, message = message)
}
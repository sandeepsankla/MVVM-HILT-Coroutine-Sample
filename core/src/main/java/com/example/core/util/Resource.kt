package com.example.core.util

sealed class Resource<out T> (val status : Status, val _data : T?, val message: String?, val statusCode: Int?= null) {

    data class Success<out R>(val data : R) : Resource<R>(
        status = Status.SUCCESS,
        _data = data,
        message = null,
        statusCode = 200
    )
    data class Loading(val isLoading : Boolean) : Resource<Nothing>(
        status = Status.LOADING,
        _data = null,
        message = null,
        statusCode = null
    )
    data class Error(val exception: String,val code: Int?) : Resource<Nothing>(
        status = Status.ERROR,
        _data = null,
        message = exception,
        statusCode = code
    )

}


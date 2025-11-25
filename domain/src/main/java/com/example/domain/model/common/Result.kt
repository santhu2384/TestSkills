package com.example.domain.model.common

sealed class Result<out T>
{
   object Loading: Result<Nothing>()
    data class Success<T>(val data:T): Result<T>()
    data class Error(val message:String): Result<Nothing>()

    companion object
    {
        fun loading()= Loading
        fun <T> success(data:T) = Success(data)
        fun error(message:String) = Error(message)
    }
}

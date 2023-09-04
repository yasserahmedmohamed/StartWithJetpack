package com.yasser.networklayer.restAPIs.response

sealed class NetworkResponseState<out T> {
    data class Success<out T>(val results: T) : NetworkResponseState<T>()
    data class GenericError(val code: Int? = null, val error: String? = null) : NetworkResponseState<Nothing>()
    object NetworkConnectionError : NetworkResponseState<Nothing>()
    object ServerError : NetworkResponseState<Nothing>()
    object AuthorizationError : NetworkResponseState<Nothing>()
}

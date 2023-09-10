package com.yasser.networklayer.restAPIs.response

sealed class NetworkResponseState<out T, out Y> {
    data class Success<out T,out Y>(val results: T) : NetworkResponseState<T, Y>()
    data class Fail<out T,out Y>(
        val errorType: NetworkCodeError? = null,
        val error: String? = null,
        val errorResponse: Y?=null
    ) :NetworkResponseState<T, Y>()
}

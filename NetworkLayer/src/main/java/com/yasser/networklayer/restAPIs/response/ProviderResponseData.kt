package com.yasser.networklayer.restAPIs.response

data class ProviderResponseData(
    val isSuccess:Boolean,
    val code:Int,
    val body:Any?
)
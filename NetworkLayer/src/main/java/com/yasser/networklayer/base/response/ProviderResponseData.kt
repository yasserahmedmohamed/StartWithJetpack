package com.yasser.networklayer.base.response

data class ProviderResponseData(
    val isSuccess:Boolean,
    val code:Int,
    val body:Any?
)
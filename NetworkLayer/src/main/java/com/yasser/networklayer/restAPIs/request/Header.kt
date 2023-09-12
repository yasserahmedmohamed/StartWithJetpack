package com.yasser.networklayer.restAPIs.request

import com.yasser.networklayer.restAPIs.interfaces.NetworkProviderInterface

data class Header(
    val headerKey : NetworkProviderInterface.Header,
    val headerValue: String
)

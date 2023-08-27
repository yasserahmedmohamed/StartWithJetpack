package com.yasser.networklayer.base.request

import java.io.File

data class ProviderRequestData(
    val requestType: NetworkRequestType,
    val endPointUrl: EndPointUrlCreator,
    val bodyParams: Map<String, Any>? = null,
    val headersParams: Map<String, Any>? = null,
    val files: List<File>? = null
)

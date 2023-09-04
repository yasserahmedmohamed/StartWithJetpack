package com.yasser.networklayer.restAPIs.request

import java.io.File

data class ProviderRequestData(
    val requestType: NetworkRequestType,
    val endPointGenerator: EndPointUrlCreator,
    val bodyParams: Map<String, Any>? = null,
    val headersParams: Map<String, Any>? = null,
    val files: List<File>? = null
)

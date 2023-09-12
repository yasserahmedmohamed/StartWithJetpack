package com.yasser.networklayer.restAPIs.interfaces

import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.response.ProviderResponseData

fun interface NetworkProviderInterface {
    /*
     what provider need to start working and it will call in the start of application
     */
//    fun startService()



    /**
     *
     * @param requestData is the data need to call API
     * @return is the model to know if response is SUCCESS with the return data or FAIL with the reason of failure
     *
     */
    suspend fun callApi(requestData: NetworkRequestBuilder): ProviderResponseData


    enum class Header(val header: String) {
        HEADER_AUTHORIZATION("Authorization"),
        HEADER_ACCEPT_RANGES("Accept-Ranges"),
        HEADER_ETAG("ETag"),
        HEADER_SERVER("Server"),
        HEADER_CONTENT_TYPE("Content-Type"),
        HEADER_CONTENT_LENGTH("Content-Length"),
        HEADER_EXPIRES("Expires"),

    }

}
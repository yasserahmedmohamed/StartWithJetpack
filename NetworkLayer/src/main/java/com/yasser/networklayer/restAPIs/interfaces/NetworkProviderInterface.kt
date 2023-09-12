package com.yasser.networklayer.restAPIs.interfaces

import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.response.ProviderResponseData

fun interface NetworkProviderInterface {


    /**
     * [requestData] is the data need to call API
     * return is the model to know if response is SUCCESS with the return data or FAIL with the reason of failure
     */
    suspend fun callApi(requestData: NetworkRequestBuilder): ProviderResponseData


}
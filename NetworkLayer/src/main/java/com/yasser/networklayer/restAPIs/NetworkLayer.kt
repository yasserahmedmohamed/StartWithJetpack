package com.yasser.networklayer.restAPIs

import com.yasser.networklayer.restAPIs.interfaces.BaseNetworkLayer
import com.yasser.networklayer.restAPIs.interfaces.NetworkProviderInterface
import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.response.NetworkResponseState

class NetworkLayer constructor(networkProvider: NetworkProviderInterface) :
    BaseNetworkLayer(networkProvider) {


    override suspend fun <T, Y> callApi(
        requestData: NetworkRequestBuilder,
        successModel: Class<T>,
        failModel: Class<Y>
    ): NetworkResponseState<T, Y> {
        // first need to call api and get response
        val responseData = getProvider().callApi(requestData)
        // after get response data whatever it success or fail will pass it to mapResponse
        return mapResponse(responseData,successModel,failModel)
    }

}
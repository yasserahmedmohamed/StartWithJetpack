package com.yasser.networklayer.restAPIs

import com.yasser.networklayer.restAPIs.interfaces.NetworkLayerInterface
import com.yasser.networklayer.restAPIs.interfaces.NetworkProviderInterface
import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.response.NetworkResponseState

class NetworkLayer constructor(networkProvider: NetworkProviderInterface) :
    NetworkLayerInterface(networkProvider) {


    override suspend fun <T,Y> callApi(requestData: NetworkRequestBuilder<T,Y>): NetworkResponseState<T,Y> {
        // first need to call api and get response
        val responseData = getProvider().callApi(requestData.getProviderData())
        // after get response data whatever it success or fail will pass it to mapResponse
        return mapResponse(responseData,requestData.successModel,requestData.failModel)
    }

}
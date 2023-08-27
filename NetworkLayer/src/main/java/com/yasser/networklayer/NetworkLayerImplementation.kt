package com.yasser.networklayer

import com.yasser.networklayer.base.networkLayerInterface.NetworkLayer
import com.yasser.networklayer.base.request.NetworkRequestBuilder
import com.yasser.networklayer.base.response.NetworkResponseState
import com.yasser.networklayer.provider.NetworkProviderInterface

class NetworkLayerImplementation constructor(networkProvider: NetworkProviderInterface) :
    NetworkLayer(networkProvider) {


    override suspend fun <T> callApi(requestData: NetworkRequestBuilder<T>): NetworkResponseState<T> {
        // first need to call api and get response
        val responseData = getProvider().callApi(requestData.getProviderData())
        // after get response data whatever it success or fail will pass it to mapResponse
        return mapResponse(responseData,requestData.responseType)
    }

}
package com.yasser.networklayer.base.networkLayerInterface

import com.yasser.networklayer.base.request.NetworkRequestBuilder
import com.yasser.networklayer.base.response.NetworkResponseState
import com.yasser.networklayer.base.response.ProviderResponseData
import com.yasser.networklayer.provider.NetworkProviderInterface
import com.google.gson.Gson
import com.yasser.networklayer.base.request.EndPointUrl

abstract class NetworkLayer constructor(private val provider: NetworkProviderInterface) {
    fun getProvider(): NetworkProviderInterface {
        return provider
    }
    /**
     *
     * @param requestData is the data need to call API
     * @return is the model to know if response is SUCCESS with the return data or FAIL with the reason of failure
     *
     */
    abstract suspend fun <T> callApi(requestData: NetworkRequestBuilder<T>): NetworkResponseState<T>

    fun <T> mapResponse(data: ProviderResponseData, mapClass: Class<T>): NetworkResponseState<T> {
        if (data.isSuccess){
           return NetworkResponseState.Success( mapToObject(data.body,mapClass))
        }else{
            TODO() // handle errors that return from the server with codes
        }
    }

    private fun <T> mapToObject(responseObject: Any?, type: Class<T>): T {
        val gson = Gson()
        val json = gson.toJson(responseObject)
        return gson.fromJson(json, type)
    }
}
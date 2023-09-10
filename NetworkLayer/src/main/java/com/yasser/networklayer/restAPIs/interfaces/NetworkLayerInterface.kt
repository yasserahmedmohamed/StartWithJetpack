package com.yasser.networklayer.restAPIs.interfaces

import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.response.NetworkResponseState
import com.yasser.networklayer.restAPIs.response.ProviderResponseData
import com.google.gson.Gson
import com.yasser.networklayer.restAPIs.response.NetworkUtils

abstract class NetworkLayerInterface constructor(private val provider: NetworkProviderInterface) {
    fun getProvider(): NetworkProviderInterface {
        return provider
    }
    /**
     *
     * @param requestData is the data need to call API
     * @return is the model to know if response is SUCCESS with the return data or FAIL with the reason of failure
     *
     */
    abstract suspend fun <T,Y> callApi(requestData: NetworkRequestBuilder<T,Y>): NetworkResponseState<T,Y>

    fun <T,Y> mapResponse(data: ProviderResponseData, successClass: Class<T>, failClass: Class<Y>): NetworkResponseState<T,Y> {
        if (data.isSuccess){
           return NetworkResponseState.Success( mapToObject(data.body,successClass))
        }else{
           return NetworkResponseState.Fail(
               errorType = NetworkUtils.getNetworkErrorType(data.code),
               errorResponse = mapToObject(data.body,failClass),
               error = ""

           )
        }
    }

    private fun <T> mapToObject(responseObject: Any?, type: Class<T>): T {
        val gson = Gson()
        val json = gson.toJson(responseObject)
        return gson.fromJson(json, type)
    }
}
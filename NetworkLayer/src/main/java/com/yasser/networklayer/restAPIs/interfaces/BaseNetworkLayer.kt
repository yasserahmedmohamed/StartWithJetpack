package com.yasser.networklayer.restAPIs.interfaces

import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.response.NetworkResponseState
import com.yasser.networklayer.restAPIs.response.ProviderResponseData
import com.google.gson.Gson
import com.yasser.networklayer.restAPIs.response.NetworkUtils

abstract class BaseNetworkLayer constructor(private val provider: NetworkProviderInterface) {
   internal fun getProvider(): NetworkProviderInterface {
        return provider
    }

    /**
     *
     * @param requestData is the data need to call API
     * @return is the model to know if response is SUCCESS with the return data or FAIL with the reason of failure
     *
     */
    abstract suspend fun <T, Y> callApi(
        requestData: NetworkRequestBuilder,
        successModel: Class<T>,
        failModel:Class<Y>
    ): NetworkResponseState<T, Y>

    suspend fun <T> callApi(
        requestData: NetworkRequestBuilder,
        successModel: Class<T>,
    ): NetworkResponseState<T,Any>{
       return callApi(requestData,successModel,Any::class.java)
    }


   internal fun <T, Y> mapResponse(
        data: ProviderResponseData,
        successClass: Class<T>,
        failClass: Class<Y>
    ): NetworkResponseState<T, Y> {
        if (data.isSuccess) {
            return NetworkResponseState.Success(mapToObject(data.body, successClass)!!)
        } else {
            return NetworkResponseState.Fail(
                errorType = NetworkUtils.getNetworkErrorType(data.code),
                errorResponseModel = mapToObject(data.body, failClass),
                error = ""

            )
        }
    }

    private fun <T> mapToObject(responseObject: Any?, type: Class<T>): T? {
        try {
            if (responseObject is String){
                val gson = Gson()
                return gson.fromJson(responseObject, type)
            }else{

                val gson = Gson()
                val json = gson.toJson(responseObject)
                return gson.fromJson(json, type)
            }

        }catch (e:Throwable){
           return null
        }

    }
}
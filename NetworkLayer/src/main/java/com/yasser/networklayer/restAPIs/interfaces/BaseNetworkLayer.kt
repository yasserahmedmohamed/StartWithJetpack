package com.yasser.networklayer.restAPIs.interfaces

import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.response.NetworkResponseState
import com.yasser.networklayer.restAPIs.response.ProviderResponseData
import com.google.gson.Gson
import com.squareup.moshi.Moshi
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
        failModel: Class<Y>
    ): NetworkResponseState<T, Y>

    suspend fun <T> callApi(
        requestData: NetworkRequestBuilder,
        successModel: Class<T>,
    ): NetworkResponseState<T, Any> {
        return callApi(requestData, successModel, Any::class.java)
    }


    internal fun <T, Y> mapResponse(
        data: ProviderResponseData,
        successClass: Class<T>,
        failClass: Class<Y>
    ): NetworkResponseState<T, Y> {
        return if (data.isSuccess) {
            NetworkResponseState.Success(mapToObject(data.body, successClass)!!)
        } else {
            NetworkResponseState.Fail(
                errorType = NetworkUtils.getNetworkErrorType(data.code),
                errorResponseModel = mapToObject(data.body, failClass),
                error = ""

            )
        }
    }

    private fun <T> mapToObject(responseObject: Any?, type: Class<T>): T? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(type)

        return try {
            if (responseObject is String) {
                jsonAdapter.fromJson(responseObject)
            } else {
                val json = jsonAdapter.toJson(type.newInstance())
                jsonAdapter.fromJson(json)
            }

        } catch (e: Throwable) {
            null
        }

    }
}
package com.yasser.networklayer.restAPIs.interfaces

import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.response.ProviderResponseData

interface NetworkProviderInterface {
    /*
     what provider need to start working and it will call in the start of application
     */
//    fun startService()

    /*
    in case of we need to update Authorization token
     */
    fun updateTokenHeader(token: String)

    /*
    in case of we need to update Authorization token
     */
    fun updateLanguageHeader(language: String)

    /**
     *
     * @param requestData is the data need to call API
     * @return is the model to know if response is SUCCESS with the return data or FAIL with the reason of failure
     *
     */
    suspend fun callApi(requestData: NetworkRequestBuilder): ProviderResponseData


    companion object{
        const val languageHeaderKey = "Lang"
        const val tokenHeaderKey = "authorization"
    }

}
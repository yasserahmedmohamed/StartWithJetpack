package com.yasser.networklayer.provider

import com.yasser.networklayer.base.request.ProviderRequestData
import com.yasser.networklayer.base.response.ProviderResponseData

interface NetworkProviderInterface {
    /*
     what provider need to start working and it will call in the start of application
     */
    fun startService()

    /*
    in case of we need to update Authorization token
     */
    fun updateToken(token: String)

    /**
     *
     * @param requestData is the data need to call API
     * @return is the model to know if response is SUCCESS with the return data or FAIL with the reason of failure
     *
     */
    suspend fun callApi(requestData: ProviderRequestData): ProviderResponseData

}
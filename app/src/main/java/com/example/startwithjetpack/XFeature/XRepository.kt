package com.example.startwithjetpack.XFeature

import android.util.Log
import com.yasser.networklayer.base.networkLayerInterface.NetworkLayer
import com.yasser.networklayer.base.request.EndPointUrl
import com.yasser.networklayer.base.request.EndPointUrlCreator
import com.yasser.networklayer.base.request.NetworkRequestBuilder
import com.yasser.networklayer.base.request.NetworkRequestType
import com.yasser.networklayer.base.response.NetworkResponseState

class XRepository constructor(val networkLayer: NetworkLayer) {

    suspend fun callApi(): NetworkResponseState<DummyProducts> {
        val requestData = NetworkRequestBuilder.Builder(DummyProducts::class.java)
            .endPointUrl(EndPointUrlCreator.Builder().addToPath("products").build())
            .requestType(NetworkRequestType.POST)
            .build()


        val responseData = networkLayer.callApi(requestData)

        return responseData
    }
}
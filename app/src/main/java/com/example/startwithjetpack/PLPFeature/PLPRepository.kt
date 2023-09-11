package com.example.startwithjetpack.PLPFeature

import com.example.startwithjetpack.PLPFeature.data.PLPResponse
import com.yasser.networklayer.restAPIs.interfaces.BaseNetworkLayer
import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.request.NetworkRequestType
import com.yasser.networklayer.restAPIs.response.NetworkResponseState

class PLPRepository constructor(val networkLayer: BaseNetworkLayer) {

    suspend fun callApi(
        categoryID: Int,
        page: Int,
        noItemsPerPage: Int,
        product_list_order: String
    ): NetworkResponseState<PLPResponse, Any> {

        val requestData = NetworkRequestBuilder.Builder()
            .endPointUrl("customer/category/$categoryID/$page/$noItemsPerPage?product_list_order=$product_list_order")
            .requestType(NetworkRequestType.GET)
            .build()


        val responseData = networkLayer.callApi(requestData, PLPResponse::class.java)

        return responseData
    }
}
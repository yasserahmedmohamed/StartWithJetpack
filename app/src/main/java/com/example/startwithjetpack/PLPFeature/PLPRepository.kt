package com.example.startwithjetpack.PLPFeature

import com.example.startwithjetpack.PLPFeature.data.PLPResponse
import com.yasser.networklayer.restAPIs.interfaces.BaseNetworkLayer
import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.request.NetworkRequestType
import com.yasser.networklayer.restAPIs.response.NetworkResponseState
import javax.inject.Inject

class PLPRepository @Inject constructor(private val networkLayer: BaseNetworkLayer) {

    suspend fun callApi(
        categoryID: Int,
        page: Int,
        noItemsPerPage: Int,
        productListOrder: String
    ): NetworkResponseState<PLPResponse, Any> {

        return networkLayer.callApi(
            NetworkRequestBuilder.Builder()
                .endPointUrl("customer/category/$categoryID/$page/$noItemsPerPage?product_list_order=$productListOrder")
                .requestMethod(NetworkRequestType.GET)
                .build(), PLPResponse::class.java
        )
    }
}
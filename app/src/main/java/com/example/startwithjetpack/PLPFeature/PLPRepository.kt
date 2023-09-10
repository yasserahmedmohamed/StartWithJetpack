package com.example.startwithjetpack.PLPFeature

import android.util.Log
import com.example.startwithjetpack.PLPFeature.data.PLPResponse
import com.yasser.networklayer.restAPIs.interfaces.NetworkLayerInterface
import com.yasser.networklayer.restAPIs.request.EndPointUrlCreator
import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.request.NetworkRequestType
import com.yasser.networklayer.restAPIs.response.NetworkResponseState

class PLPRepository constructor(val networkLayer: NetworkLayerInterface) {

    suspend fun callApi(
        categoryID: Int,
        page: Int,
        noItemsPerPage: Int,
        product_list_order: String
    ): NetworkResponseState<PLPResponse,Any> {


        val endPointUrlCreator =
            EndPointUrlCreator.Builder()
                .setEndPointUrl("customer/category/{0}/{1}/{2}?product_list_order={3}")
                .setValues(categoryID,page,noItemsPerPage,product_list_order)

        Log.e("endPointUrlCreator2",endPointUrlCreator.url)

        val requestData = NetworkRequestBuilder.Builder<PLPResponse,Any>()
            .endPointUrl(endPointUrlCreator)
            .successModel(PLPResponse::class.java)
            .failModel(Any::class.java)
            .headerParams("Authorization", "Bearer 7i7xck3uns4eezn7isg6cngyjda5wzc2")
            .requestType(NetworkRequestType.GET)
            .build()


        val responseData = networkLayer.callApi(requestData)

        return responseData
    }
}
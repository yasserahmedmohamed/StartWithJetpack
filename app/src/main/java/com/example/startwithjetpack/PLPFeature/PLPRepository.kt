package com.example.startwithjetpack.PLPFeature

import com.yasser.networklayer.base.networkLayerInterface.NetworkLayer
import com.yasser.networklayer.base.request.EndPointUrlCreator
import com.yasser.networklayer.base.request.NetworkRequestBuilder
import com.yasser.networklayer.base.request.NetworkRequestType
import com.yasser.networklayer.base.response.NetworkResponseState

class PLPRepository constructor(val networkLayer: NetworkLayer) {

    suspend fun callApi(categoryID:Int,page:Int,noItemsPerPage:Int,product_list_order:String): NetworkResponseState<PLPResponse> {

        val endPoint = EndPointUrlCreator.Builder()
            .addToPath("customer")
            .addToPath("category")
            .addToPath(categoryID.toString())
            .addToPath(page.toString())
            .addToPath(noItemsPerPage.toString())
            .addToQuery("product_list_order",product_list_order)
            .build()

        val requestData = NetworkRequestBuilder.Builder(PLPResponse::class.java)
            .endPointUrl(endPoint)
            .headerParams("Authorization","Bearer 7i7xck3uns4eezn7isg6cngyjda5wzc2")
            .requestType(NetworkRequestType.GET)
            .build()


        val responseData = networkLayer.callApi(requestData)

        return responseData
    }
}
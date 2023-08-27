package com.example.startwithjetpack.XFeature

import com.yasser.networklayer.base.response.BaseApiResponse

data class DummyProducts(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
): BaseApiResponse()
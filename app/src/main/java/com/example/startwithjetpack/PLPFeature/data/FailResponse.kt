package com.example.startwithjetpack.PLPFeature.data

data class FailResponse(
    val message: String,
    val parameters: Parameters,
    val trace: String
)

data class Parameters(
    val resources: String
)
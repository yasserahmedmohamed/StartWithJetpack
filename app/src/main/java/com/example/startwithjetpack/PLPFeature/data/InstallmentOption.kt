package com.example.startwithjetpack.PLPFeature.data

data class InstallmentOption(
    val admin_fee: Int,
    val default_installments: Int,
    val discount_for_interest: Int,
    val down_payment_amount: Int,
    val interest_rate: Double,
    val mandatory_downpayment: Int,
    val minimum_down_payment: Int,
    val months: String,
    val price_for_month: Int,
    val recommended_downpayment_amount: Int
)
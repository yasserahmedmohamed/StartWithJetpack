package com.example.startwithjetpack.PLPFeature

data class Product(
    val attributes: List<Attribute>,
    val badge_list: List<Any>,
    val badges: List<Any>,
    val brand: String,
    val btech_simple_bundle: Int,
    val cart_discount: String,
    val cart_promo_code: String,
    val category_new: String,
    val category_tag: Any,
    val category_tag_list: List<Any>,
    val coupon: List<Coupon>,
    val coupon_details: List<CouponDetail>,
    val extra_services: Any,
    val gfk_brand_16299: String,
    val gift_product_sku: String,
    val id: Int,
    val image: String,
    val installment_options: List<InstallmentOption>,
    val is_returning_customer: Int,
    val minicash: List<Minicash>,
    val minicash_campaign: Int,
    val minicash_interest_discount: String,
    val minimum_mc_price_mcn: String,
    val minimum_mc_price_mcr: Int,
    val name: String,
    val offer_badges: List<Any>,
    val price: Int,
    val promotion_days_count: Int,
    val regular_price: Int,
    val review_avg_score: Double,
    val review_count: Int,
    val sku: String,
    val special_from_date: String,
    val special_tag: List<SpecialTag>,
    val special_to_date: String,
    val specification: List<Any>,
    val tier_price: List<Any>,
    val type: String,
    val winner_vendor_id: Int
)
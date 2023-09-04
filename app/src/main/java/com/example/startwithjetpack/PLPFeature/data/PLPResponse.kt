package com.example.startwithjetpack.PLPFeature.data

data class PLPResponse(
    val active_filters: List<Any>,
    val active_minicash_limit: List<Any>,
    val children: String,
    val filters: List<Filter>,
    val id: Int,
    val image: String,
    val is_active: Boolean,
    val level: Int,
    val max_price: Int,
    val min_price: Int,
    val minicash_entry: Int,
    val minicash_limit: List<MinicashLimit>,
    val name: String,
    val parent_id: Int,
    val position: Int,
    val products: List<Product>,
    val products_count: Int,
    val sort_options: List<SortOption>
)
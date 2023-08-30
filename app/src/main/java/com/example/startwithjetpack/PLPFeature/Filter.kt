package com.example.startwithjetpack.PLPFeature

data class Filter(
    val code: String,
    val filter_options: List<FilterOption>,
    val id: Int,
    val is_swatch: Boolean,
    val label: String,
    val position: Int
)
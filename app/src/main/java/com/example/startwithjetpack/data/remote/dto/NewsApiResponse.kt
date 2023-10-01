package com.example.startwithjetpack.data.remote.dto

import com.example.startwithjetpack.domain.model.Article

data class NewsApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
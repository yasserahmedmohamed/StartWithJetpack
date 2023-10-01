package com.example.startwithjetpack.presentation.search

import androidx.paging.PagingData
import com.example.startwithjetpack.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery:String = "",
    val articles:Flow<PagingData<Article>>?=null
)
package com.example.startwithjetpack.domain.repository

import androidx.paging.PagingData
import com.example.startwithjetpack.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadLines(countryCode: String, searchWords: String? = null): Flow<PagingData<Article>>
    fun searchNews(sources: List<String>, searchWords: String): Flow<PagingData<Article>>
}
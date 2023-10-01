package com.example.startwithjetpack.domain.useCases.news

import androidx.paging.PagingData
import com.example.startwithjetpack.domain.model.Article
import com.example.startwithjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class TopHeadLinesNewsUseCase(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(countryCode:String,searchWords:String): Flow<PagingData<Article>> {
        return newsRepository.getTopHeadLines(countryCode, searchWords)
    }
}
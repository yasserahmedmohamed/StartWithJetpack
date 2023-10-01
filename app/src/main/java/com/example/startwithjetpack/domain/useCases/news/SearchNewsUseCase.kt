package com.example.startwithjetpack.domain.useCases.news

import androidx.paging.PagingData
import com.example.startwithjetpack.domain.model.Article
import com.example.startwithjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources:List<String>,searchWords:String): Flow<PagingData<Article>> {
        return newsRepository.searchNews(sources, searchWords)
    }
}
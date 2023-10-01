package com.example.startwithjetpack.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.startwithjetpack.data.remote.SearchNewsDataSource
import com.example.startwithjetpack.data.remote.TopHeadLinesDataSource
import com.example.startwithjetpack.domain.model.Article
import com.example.startwithjetpack.domain.repository.NewsRepository
import com.example.startwithjetpack.utils.Constants
import com.yasser.networklayer.restAPIs.NetworkLayer
import kotlinx.coroutines.flow.Flow

class NewsRepositoryIml(
    private val networkLayer: NetworkLayer
):NewsRepository {
    override fun getTopHeadLines(
        countryCode: String,
        searchWords: String?
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(Constants.PagingPageSize),
            pagingSourceFactory = {
                TopHeadLinesDataSource(networkLayer,countryCode)
            }
        ).flow
    }

    override fun searchNews(sources: List<String>, searchWords: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(Constants.PagingPageSize),
            pagingSourceFactory = {
                SearchNewsDataSource(networkLayer,sources.joinToString(separator = ","),searchWords)
            }
        ).flow
    }


}
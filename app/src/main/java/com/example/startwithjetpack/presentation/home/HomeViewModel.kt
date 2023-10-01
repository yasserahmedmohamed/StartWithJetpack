package com.example.startwithjetpack.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.startwithjetpack.domain.model.Article
import com.example.startwithjetpack.domain.useCases.news.TopHeadLinesNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val topHeadLinesNewsUseCase: TopHeadLinesNewsUseCase):ViewModel() {



    fun getTopHeadLines(countryCode:String,querySearch:String=""): Flow<PagingData<Article>> {
       return topHeadLinesNewsUseCase(countryCode,querySearch)
    }
}
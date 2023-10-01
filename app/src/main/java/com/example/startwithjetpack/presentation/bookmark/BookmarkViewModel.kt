package com.example.startwithjetpack.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startwithjetpack.data.local.NewsDao
import com.example.startwithjetpack.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    newsUseCases: NewsDao
) : ViewModel() {

    val articles = mutableStateOf<List<Article>>(listOf())

    init {
        newsUseCases.getArticles().onEach {
            articles.value = it
        }.launchIn(viewModelScope)
    }
}
package com.example.startwithjetpack.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.startwithjetpack.domain.useCases.news.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(private val searchNewsUseCase: SearchNewsUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state



    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchNews -> {
                searchNews()
            }
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.q)

            }
        }
    }


    private fun searchNews(){
        val articles = searchNewsUseCase(listOf("bbc-news", "abc-news", "al-jazeera-english"),state.value.searchQuery)
            .cachedIn(viewModelScope)

        _state.value = _state.value.copy(articles = articles)
    }
}
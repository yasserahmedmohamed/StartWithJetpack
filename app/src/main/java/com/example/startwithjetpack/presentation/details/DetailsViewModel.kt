package com.example.startwithjetpack.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startwithjetpack.data.local.NewsDao
import com.example.startwithjetpack.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val newsDao: NewsDao) : ViewModel() {

    var message by mutableStateOf<String?>(null)
        private set

    fun saveArticle(toSaveArticle: Article){
        viewModelScope.launch {
            val article = newsDao.getArticleByUrl(url = toSaveArticle.url)
            if (article == null){
                upsertArticle(article = toSaveArticle)
            }else{
                deleteArticle(article = toSaveArticle)
            }
        }
    }


    private suspend fun deleteArticle(article: Article) {
        newsDao.delete(article = article)
        message = "Article deleted"
    }

    private suspend fun upsertArticle(article: Article) {
        newsDao.save(article = article)
        message = "Article Saved"
    }
}
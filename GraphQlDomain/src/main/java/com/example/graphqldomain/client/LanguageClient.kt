package com.example.graphqldomain.client

import com.example.graphqldomain.models.Country
import com.example.graphqldomain.models.Language

interface LanguageClient {
    suspend fun getLanguages(): List<Language>
}
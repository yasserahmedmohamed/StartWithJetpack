package com.example.graphqldata

import com.example.graphqldomain.client.LanguageClient
import com.example.graphqldomain.models.Language

class LanguageClientProvider:LanguageClient {
    override suspend fun getLanguages(): List<Language> {
        return listOf(
            Language("1","arabic","true",0),
            Language("2","english","true",1),
            Language("3","france","true",1),
            Language("4","german","true",1),
            Language("5","russian","true",1))
    }
}
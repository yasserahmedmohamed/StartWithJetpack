package com.example.graphqldata

import com.example.graphqldomain.client.ContinentClient
import com.example.graphqldomain.client.LanguageClient
import com.example.graphqldomain.models.Continent
import com.example.graphqldomain.models.Language

class ContinentClientProvider:ContinentClient {


    override suspend fun getContinents(): List<Continent> {
        return listOf(
            Continent("Africa","AF"),
            Continent("Antarctica","AN"),
            Continent("Asia","AS"),
            Continent("Europe","EU"),
            Continent("North America","NA"),
        )
    }
}
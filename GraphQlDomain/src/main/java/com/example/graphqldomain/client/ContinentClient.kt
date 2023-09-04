package com.example.graphqldomain.client

import com.example.graphqldomain.models.Continent

interface ContinentClient {
    suspend fun getContinents(): List<Continent>
}
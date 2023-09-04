package com.example.graphqldomain.client

import com.example.graphqldomain.models.Country
import com.example.graphqldomain.models.CountryDetails

interface CountryClient {
    suspend fun getCountries(): List<Country>
    suspend fun getCountry(code: String): CountryDetails?
}
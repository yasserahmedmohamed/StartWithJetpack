package com.example.graphqldata

import com.example.graphqldomain.client.CountryClient
import com.example.graphqldomain.models.Country
import com.example.graphqldomain.models.CountryDetails

class CountryClientProvider:CountryClient {
    override suspend fun getCountries(): List<Country> {
        return listOf(
            Country("1","test 1","","cairo"),
            Country("2","test 2","","new cairo"),
            Country("3","test 3","","aswan"),
            Country("4","test 4","","dahab"),

        )
    }

    override suspend fun getCountry(code: String): CountryDetails? {
        return CountryDetails("4","test 4","","cairo","", listOf(),"")
    }
}
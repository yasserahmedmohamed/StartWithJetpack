package com.example.graphqldata.graphQl

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.graphqldata.ContinentClientProvider
import com.example.graphqldata.CountryClientProvider
import com.example.graphqldata.LanguageClientProvider
import com.example.graphqldomain.client.ContinentClient
import com.example.graphqldomain.client.CountryClient
import com.example.graphqldomain.client.LanguageClient
import kotlin.reflect.typeOf

class ApolloProvider : GraphQlProviderInterface {
    override fun <T> getClientProvider(type: Class<T>): T {
//        if (type.name.equals(CountryClient::class.java.name )){
//            return CountryClientProvider() as T
//
//        }
        when (type.name) {
            CountryClient::class.java.name -> {
                return CountryClientProvider() as T
            }

            LanguageClient::class.java.name -> {
                return LanguageClientProvider() as T
            }

            else -> {
                return ContinentClientProvider() as T
            }
        }

    }
}
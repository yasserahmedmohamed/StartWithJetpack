package com.example.graphqldata.graphQl

interface GraphQlProviderInterface {
    fun <T> getClientProvider(type:Class<T>):T
}
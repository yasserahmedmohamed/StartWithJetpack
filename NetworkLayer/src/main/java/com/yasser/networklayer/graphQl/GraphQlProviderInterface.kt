package com.yasser.networklayer.graphQl

interface GraphQlProviderInterface {
    fun <T> getClientProvider(type:Class<T>):T
}
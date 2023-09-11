package com.example.graphqldata.graphQl

import android.content.ContentProviderClient
import com.example.graphqldata.CountryClientProvider

class GraphQlLayer(val provider: GraphQlProviderInterface) {



    fun <T> getClientProvider(providerType:Class<T>):T{
      return  provider.getClientProvider(providerType)
    }
}
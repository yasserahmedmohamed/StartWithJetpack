package com.example.startwithjetpack

import com.yasser.networklayer.restAPIs.NetworkLayer
import com.yasser.networklayer.restAPIs.enums.Header.HEADER_AUTHORIZATION
import com.yasser.networklayer.restAPIs.interfaces.BaseNetworkLayer
import com.yasser.networklayer.restAPIs.provider.retrofit.RetrofitNetworkProvider
import com.yasser.networklayer.restAPIs.request.Header
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNetworkProvider(
    ): RetrofitNetworkProvider {
        return RetrofitNetworkProvider(
            Header(HEADER_AUTHORIZATION,"Bearer 7i7xck3uns4eezn7isg6cngyjda5wzc2"),
        )
    }
    @Provides
    @Singleton
    fun networkProvider(retrofitProvider: RetrofitNetworkProvider): BaseNetworkLayer {
        return NetworkLayer(retrofitProvider)
    }


}
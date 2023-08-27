package com.yasser.networklayer.provider.retrofit

import com.yasser.networklayer.base.request.NetworkRequestType
import com.yasser.networklayer.base.request.ProviderRequestData
import com.yasser.networklayer.base.response.ProviderResponseData
import com.yasser.networklayer.provider.NetworkProviderInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkProvider: NetworkProviderInterface {

    //******************************************************
    // ------------------------------------------------------------------------------------------
    // ------------- The Retrofit Clients & Interfaces ------------------------------------------
    private lateinit var okkHttpclient: OkHttpClient
    private var retrofit: RetrofitAPIs? = null
    private var userToken:String = ""
    // ------------------------------------------------------------------------------------------
    //******************************************************

    override fun startService() {

        okkHttpclient = provideOkHttpClient(userToken)
        retrofit = Retrofit.Builder()
            .client(okkHttpclient)
            .baseUrl("https://dummyjson.com/")//Build.Config.BaseUrl
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(RetrofitAPIs::class.java)

    }

    override fun updateToken(token: String) {
        userToken = token
        startService()
    }

    override suspend fun callApi(requestData: ProviderRequestData): ProviderResponseData {
        when (requestData.requestType) {
            NetworkRequestType.GET -> {
                try {
                    retrofit?.let {
                        val response = it.requestGETMethod(
                            url = requestData.endPointUrl.url,
                            headers = requestData.headersParams ?: hashMapOf(),
                            params =  hashMapOf()
                        )


                        return ProviderResponseData(response.isSuccessful,response.code(),response.body())
                    }
                } catch (e: Exception) {
                    return ProviderResponseData(false,404,null)
                }

            }

            NetworkRequestType.POST -> {
                try {
                    retrofit?.let {
                        val response = it.requestPOSTMethod(
                            url = requestData.endPointUrl.url,
                            headers = requestData.headersParams ?: hashMapOf(),
                            params = requestData.bodyParams ?: hashMapOf()
                        )

                        return ProviderResponseData(response.isSuccessful,response.code(),response.body())
                    }
                } catch (e: Exception) {
                    return ProviderResponseData(false,404,null)
                }
            }
        }

        throw Exception("selected RequestType is not implemented in retrofit provider")
    }



    private fun provideOkHttpClient(token: String=""):OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpBuilder = OkHttpClient.Builder()
//            .connectTimeout(currentTimeOut, TimeUnit.SECONDS)
//            .writeTimeout(currentTimeOut, TimeUnit.SECONDS) // write timeout
//            .readTimeout(currentTimeOut, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .connectionSpecs(
                listOf(
                    ConnectionSpec.MODERN_TLS,
                    ConnectionSpec.COMPATIBLE_TLS,
                    ConnectionSpec.CLEARTEXT
                )
            )
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(null)

        if (token.isNotEmpty()) {
            val reqToken = if (token.contains("Bearer")) token else "Bearer $token"
            okHttpBuilder.addInterceptor { chain ->
                val request = chain.request().newBuilder()

                    .addHeader("authorization", reqToken)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build()
                return@addInterceptor chain.proceed(request)
            }
        }

        return okHttpBuilder.build()
    }

}
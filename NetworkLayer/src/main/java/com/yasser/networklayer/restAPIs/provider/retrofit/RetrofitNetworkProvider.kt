package com.yasser.networklayer.restAPIs.provider.retrofit

import com.yasser.networklayer.restAPIs.request.NetworkRequestType
import com.yasser.networklayer.restAPIs.response.ProviderResponseData
import com.yasser.networklayer.restAPIs.interfaces.NetworkProviderInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.yasser.networklayer.restAPIs.request.Header
import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import okhttp3.ConnectionSpec
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
class RetrofitNetworkProvider(private vararg val headers: Header) :
    NetworkProviderInterface {


    //******************************************************
    // ------------- The Retrofit Needs --------------------
    private var okkHttpclient: OkHttpClient? = null
    private var retrofit: RetrofitAPIs? = null
    private var currentBaseUrl = ""
    private var currentTimeOut: Long = 0
    // ------------------------------------------------------
    //******************************************************




    override suspend fun callApi(requestData: NetworkRequestBuilder): ProviderResponseData {

        var thereIsChangeInRequestData = false


        if (requestData.timeoutInSeconds != currentTimeOut || okkHttpclient == null) {
            currentTimeOut = requestData.timeoutInSeconds
            thereIsChangeInRequestData = true
        }

        if (currentBaseUrl != requestData.baseUrl) {
            currentBaseUrl = requestData.baseUrl
            thereIsChangeInRequestData = true
        }

        if (thereIsChangeInRequestData || retrofit == null) {
            provideOkHttpClient()
            provideRetrofitCaller()
        }

        try {

            val response = when (requestData.requestMethod) {

                NetworkRequestType.GET -> {
                    retrofit!!.requestGETMethod(
                        endPointUrl = requestData.endPointUrl,
                        headers = requestData.headersParams ?: hashMapOf(),
                        params = hashMapOf()
                    )

                }

                NetworkRequestType.POST -> {

                    retrofit!!.requestPOSTMethod(
                        endPointUrl = requestData.endPointUrl,
                        headers = requestData.headersParams ?: hashMapOf(),
                        params = requestData.bodyParams ?: hashMapOf()
                    )

                }

                NetworkRequestType.POST_WITH_FILES -> {
                    val params = HashMap<String, RequestBody>()
                    val files = ArrayList<MultipartBody.Part>()

                    requestData.files?.forEach {
                        val requestFile = it.asRequestBody("image/*".toMediaTypeOrNull())

                        val body =
                            MultipartBody.Part.createFormData("files", it.name, requestFile)
                        files.add(body)
                    }

                    requestData.bodyParams?.forEach {
                        params[it.key] = it.value.toString().toRequestBody(MultipartBody.FORM)
                    }


                    retrofit!!.requestPOSTWithMultiPartMethod(
                        endPointUrl = requestData.endPointUrl,
                        headers = requestData.headersParams ?: hashMapOf(),
                        params = params,
                        files = files
                    )

                }

                NetworkRequestType.PUT -> {

                    retrofit!!.requestPUTMethod(
                        endPointUrl = requestData.endPointUrl,
                        headers = requestData.headersParams ?: hashMapOf(),
                        params = requestData.bodyParams ?: hashMapOf()
                    )
                }

                NetworkRequestType.DELETE -> {
                    retrofit!!.requestDELETEMethod(
                        endPointUrl = requestData.endPointUrl,
                        headers = requestData.headersParams ?: hashMapOf(),
                        params = requestData.bodyParams ?: hashMapOf()
                    )
                }

            }


            return if (response.isSuccessful) {
                ProviderResponseData(
                    response.isSuccessful,
                    response.code(),
                    response.body()
                )
            } else {
                ProviderResponseData(
                    response.isSuccessful,
                    response.code(),
                    response.errorBody()?.string()

                )
            }


        } catch (e: Throwable) {
            return when (e) {

                is HttpException -> {
                    ProviderResponseData(
                        false,
                        e.code(),
                        e.message()
                    )

                }

                is IOException -> {

                    return ProviderResponseData(
                        false,
                        -1,
                        "No Internet Connection"
                    )
                }


                else -> {
                    return ProviderResponseData(
                        false,
                        -1,
                        "Unknown Error"
                    )

                }
            }


        }


    }


    private fun provideRetrofitCaller() {
        retrofit = Retrofit.Builder()
            .client(okkHttpclient!!)
            .baseUrl(currentBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(RetrofitAPIs::class.java)
    }

    private fun provideOkHttpClient() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(currentTimeOut, TimeUnit.SECONDS)
            .writeTimeout(currentTimeOut, TimeUnit.SECONDS) // write timeout
            .readTimeout(currentTimeOut, TimeUnit.SECONDS)
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

        okHttpBuilder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            headers.forEach {
                request.addHeader(it.headerKey.header, it.headerValue)
            }


            return@addInterceptor chain.proceed(request.build())
        }


        okkHttpclient = okHttpBuilder.build()
    }


}
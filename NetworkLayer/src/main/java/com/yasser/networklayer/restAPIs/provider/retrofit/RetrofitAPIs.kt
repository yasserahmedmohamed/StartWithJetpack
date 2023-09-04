package com.yasser.networklayer.restAPIs.provider.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * Retrofit APIs Handles the different methods supported by the retrofit
 */
internal interface RetrofitAPIs {
    /**
     * Do a request using retrofit using POST HTTP method
     *
     * @param url The url of the endpoint
     *
     * @param params The parameters associated with the request
     *
     * @param headers The headers associated with the request
     */
    @POST
    @JvmSuppressWildcards
    suspend fun requestPOSTMethod(
        @Url url: String,
        @HeaderMap headers: Map<String, Any>,
        @Body params:  Map<String, Any>
    ): Response<Any>

    /**
     * Do a request using retrofit using GET HTTP method
     *
     * @param url The url of the endpoint
     *
     * @param params The parameters associated with the request
     *
     * @param headers The headers associated with the request
     */
    @GET
    @JvmSuppressWildcards
    suspend fun requestGETMethod(
        @Url url: String,
        @QueryMap(encoded = true) params: Map<String, Any>,
        @HeaderMap headers: Map<String, Any>
    ): Response<Any>



    companion object {
        /**
         * The initial invoke
         */
        operator fun invoke() {

        }
    }
}
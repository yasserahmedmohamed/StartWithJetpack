package com.yasser.networklayer.base.request

import com.yasser.networklayer.base.response.BaseApiResponse
import java.io.File

class NetworkRequestBuilder<T> private constructor(
    val requestType: NetworkRequestType,
    val endPointUrl: EndPointUrlCreator,
    val bodyParams: Map<String, Any>? = null,
    val headerParams: Map<String, Any>? = null,
    val files: List<File>? = null,
    val responseType :Class<T>
    ) {

    fun getProviderData(): ProviderRequestData {
        return ProviderRequestData(
            requestType = requestType,
            endPointUrl = endPointUrl,
            bodyParams = bodyParams,
            headersParams = headerParams,
            files = files,
        )
    }
    class Builder<T>(private val responseType: Class<T>) {
        private var requestType: NetworkRequestType? = null

        private var endPointUrl: EndPointUrlCreator? = null

        private var bodyParams: Map<String, Any>? = null

        private var headerParams= HashMap<String, Any>()

        private var files: List<File>? = null


        fun requestType(requestType: NetworkRequestType) = apply {
            this.requestType = requestType
        }

        fun endPointUrl(endPointUrl: EndPointUrlCreator) = apply {
            this.endPointUrl = endPointUrl
        }

        fun bodyParams(params: Map<String, Any>) = apply {
            this.bodyParams = params
        }

        fun headerParams(key:String,value:String) = apply {
            this.headerParams[key] = value
        }

        fun files(files: List<File>) = apply {
            this.files = files
        }

        /* in this function will add all validations on request parameters */
        fun build(): NetworkRequestBuilder<T> {
            if (requestType == null)
                throw Exception("please select request type")
            if (endPointUrl==null) {
                throw Exception("endPointUrl can not be empty")
            }

            return NetworkRequestBuilder(
                requestType = requestType!!,
                endPointUrl = endPointUrl!!,
                bodyParams = bodyParams,
                headerParams = headerParams,
                files = files,
                responseType = responseType
            )
        }

    }
}

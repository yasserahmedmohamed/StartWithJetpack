package com.yasser.networklayer.restAPIs.request

import java.io.File

class NetworkRequestBuilder<T,Y> private constructor(
    val requestType: NetworkRequestType,
    val endPointUrl: EndPointUrlCreator,
    val bodyParams: Map<String, Any>? = null,
    val headerParams: Map<String, Any>? = null,
    val files: List<File>? = null,
    val successModel :Class<T>,
    val failModel:Class<Y>
    ) {

    internal fun getProviderData(): ProviderRequestData {
        return ProviderRequestData(
            requestType = requestType,
            endPointGenerator = endPointUrl,
            bodyParams = bodyParams,
            headersParams = headerParams,
            files = files,
        )
    }
    class Builder<T,Y> {
        private var requestType: NetworkRequestType? = null

        private var endPointUrl: EndPointUrlCreator? = null

        private var bodyParams: Map<String, Any>? = null

        private var headerParams= HashMap<String, Any>()

        private var files: List<File>? = null

        private lateinit var successModel:Class<T>
        private lateinit var failModel:Class<Y>


        fun requestType(requestType: NetworkRequestType) = apply {
            this.requestType = requestType
        }

        fun successModel(successModel:Class<T>) = apply {
            this.successModel = successModel
        }

        fun failModel(failModel:Class<Y>) = apply {
            this.failModel = failModel
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
        fun build(): NetworkRequestBuilder<T,Y> {
            if (requestType == null)
                throw Exception("please select request type")
            if (endPointUrl==null) {
                throw Exception("endPointUrl can not be empty")
            }

            if (successModel==null){
                throw Exception("successModel can not be empty")
            }

            if (failModel==null){
                throw Exception("failModel can not be empty")
            }

            return NetworkRequestBuilder(
                requestType = requestType!!,
                endPointUrl = endPointUrl!!,
                bodyParams = bodyParams,
                headerParams = headerParams,
                files = files,
                successModel = this.successModel!!,
                failModel = failModel!!
            )
        }

    }
}

package com.yasser.networklayer.restAPIs.request

import com.yasser.networklayer.BuildConfig
import java.io.File

class NetworkRequestBuilder private constructor(
    val requestType: NetworkRequestType,
    val endPointUrl: String,
    val bodyParams: Map<String, Any>?,
    val headersParams: Map<String, String>?,
    val files: List<File>?,
    val timeoutInSeconds: Long,
    val baseUrl: String
) {

    class Builder {
        private var requestType: NetworkRequestType? = null

        private var endPointUrl: String? = null

        private var timeoutInSeconds: Long? = null

        private var baseUrl: String? = null


        private var bodyParams: Map<String, Any>? = null

        private var headerParams = HashMap<String, String>()

        private var files: List<File>? = null

        fun requestType(requestType: NetworkRequestType) = apply {
            this.requestType = requestType
        }

        fun endPointUrl(endPointUrl: String) = apply {
            this.endPointUrl = endPointUrl
        }

        fun bodyParams(params: Map<String, Any>) = apply {
            this.bodyParams = params
        }

        fun headerParams(key: String, value: String) = apply {
            this.headerParams[key] = value
        }

        fun baseUrl(url: String) = apply {
            this.baseUrl = url
        }

        fun requestTimeOut(seconds: Long) = apply {
            this.timeoutInSeconds = seconds
        }

        fun files(files: List<File>) = apply {
            this.files = files
        }

        /* in this function will add all validations on request parameters */
        fun build(): NetworkRequestBuilder {
            if (requestType == null)
                throw Exception("requestType not set in NetworkRequestBuilder")
            if (endPointUrl == null) {
                throw Exception("endPointUrl not set in NetworkRequestBuilder")
            }
            if (timeoutInSeconds == null) {
                timeoutInSeconds = 60
            }

            if (baseUrl == null) {
                baseUrl = BuildConfig.BASE_URL
            }
            return NetworkRequestBuilder(
                requestType = requestType!!,
                endPointUrl = endPointUrl!!,
                timeoutInSeconds = timeoutInSeconds!!,
                bodyParams = bodyParams,
                headersParams = headerParams,
                files = files,
                baseUrl = baseUrl!!
            )


        }

    }
}

package com.yasser.networklayer.restAPIs.request

class EndPointUrlCreator private constructor(
    val url:String
) {

    class Builder{

        fun setEndPointUrl(url: String): BuildUrl {
            return BuildUrl(url)
        }

        class BuildUrl constructor(private var url:String){

            fun appendQueryParam(key:String,value:Any) = apply {
                if (!url.contains("?")){
                    url +="?"
                }
                url += "&$key=$value"
            }


            fun setValues(vararg separatorList:Any) = apply{

                separatorList.forEachIndexed { index, s ->
                    url =  url.replace("{$index}",s.toString())
                }
            }

            fun build(): EndPointUrlCreator{
                return EndPointUrlCreator(url)
            }
        }
    }
}
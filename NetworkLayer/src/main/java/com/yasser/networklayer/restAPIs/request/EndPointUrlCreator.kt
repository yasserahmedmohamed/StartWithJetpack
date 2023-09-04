package com.yasser.networklayer.restAPIs.request

class EndPointUrlCreator private constructor(
   val url:String
) {

    class Builder{

        fun setEndPointUrl(url: String): BuildUrl {
            return BuildUrl(url)
        }

        class BuildUrl constructor(private val url:String){
            fun setValues(vararg separatorList:Any): EndPointUrlCreator {
                var newUrl = url
                separatorList.forEachIndexed { index, s ->
                   newUrl =  newUrl.replace("{$index}",s.toString())
                }
                return EndPointUrlCreator(newUrl)
            }
        }
    }
}
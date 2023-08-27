package com.yasser.networklayer.base.request

class EndPointUrlCreator private constructor(
   val url:String
) {



    class Builder{
        private val queryParams= ArrayList<Pair<String,String>>()
        private val pathParams = ArrayList<String>()

        fun addToQuery(key:String,value:String) = apply {
            queryParams.add(Pair(key,value))
        }
        fun addToPath(path:String) = apply {
            pathParams.add(path)
        }


        fun build(): EndPointUrlCreator {
            var url = ""
            pathParams.forEach {
                url +="$it/"
            }
            if (queryParams.size>0){
                url+="?"
            }
            queryParams.forEach {
                url+="${it.first}=${it.second}"
            }
            return EndPointUrlCreator(url)
        }
    }
}

@Target(AnnotationTarget.LOCAL_VARIABLE)
annotation class EndPointUrl(val url:String)

@Target(AnnotationTarget.FIELD)
annotation class PATH(val key:String)

@Target(AnnotationTarget.FIELD)
annotation class QUERY(val key:String)
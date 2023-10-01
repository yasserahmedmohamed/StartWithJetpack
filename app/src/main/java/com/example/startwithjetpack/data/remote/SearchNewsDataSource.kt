package com.example.startwithjetpack.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.startwithjetpack.BuildConfig
import com.example.startwithjetpack.data.remote.dto.NewsApiResponse
import com.example.startwithjetpack.domain.model.Article
import com.example.startwithjetpack.utils.Constants
import com.yasser.networklayer.restAPIs.NetworkLayer
import com.yasser.networklayer.restAPIs.request.EndPointUrlCreator
import com.yasser.networklayer.restAPIs.request.NetworkRequestBuilder
import com.yasser.networklayer.restAPIs.request.NetworkRequestType
import com.yasser.networklayer.restAPIs.response.NetworkResponseState
import java.io.IOException

class SearchNewsDataSource(
    private val networkLayer: NetworkLayer,
    private val sources: String,
    private val searchQuery:String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val page = params.key ?: 1

        val endPoint = EndPointUrlCreator.Builder()
            .setEndPointUrl("everything")
            .appendQueryParam("apiKey",BuildConfig.NEWS_API_KEY)
            .appendQueryParam("page",page)
            .appendQueryParam("pageSize",Constants.PagingPageSize)
            .appendQueryParam("sources",sources)
            .appendQueryParam("q",searchQuery)

            .build()


        val request = NetworkRequestBuilder
            .Builder()
            .requestMethod(NetworkRequestType.GET)
            .endPointUrl(
              endPoint.url
            )
            .build()



        return try {
            val response = networkLayer.callApi(request, NewsApiResponse::class.java)
            when (response) {
                is NetworkResponseState.Success -> {
                    val nextKey = if (response.results.articles.isNotEmpty()) page + 1 else null

                    LoadResult.Page(
                        data = response.results.articles,
                        prevKey = null,
                        nextKey = nextKey
                    )

                }

                is NetworkResponseState.Fail -> {
                    LoadResult.Error(
                        Throwable(
                            message = response.errorType?.name
                                ?: response.errorResponseModel.toString()
                        )
                    )

                }
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }


}
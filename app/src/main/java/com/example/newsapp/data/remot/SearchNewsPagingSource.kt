package com.example.newsapp.data.remot

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remot.dao.NewsApi

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val search: String
) : PagingSource<Int , Article>() {
    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {ancherPosition->
            val ancherPage = state.closestPageToPosition(ancherPosition)
            ancherPage?.prevKey?.plus(1)?: ancherPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key?:1
        return try {
            val newsResponce = newsApi.getSearchResult(
                page =  page,
                q = search
            )
            totalNewsCount += newsResponce.articles.size
            val article = newsResponce.articles.distinctBy { it.title }
            LoadResult.Page(
                data = article,
                nextKey = if (totalNewsCount==newsResponce.totalResults) null else page+1 ,
                prevKey = null
            )
        }catch (e: Exception){
            Log.d("Search News", "${e.message}")
            LoadResult.Error(
                throwable = e
            )
        }
    }
}
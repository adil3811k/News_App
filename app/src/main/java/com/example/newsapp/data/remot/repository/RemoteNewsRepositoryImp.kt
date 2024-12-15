package com.example.newsapp.data.remot.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remot.Article
import com.example.newsapp.data.remot.GetHeadLinePagingSource
import com.example.newsapp.data.remot.SearchNewsPagingSource
import com.example.newsapp.data.remot.dao.NewsApi
import com.example.newsapp.domain.RemoteNewsRepository
import kotlinx.coroutines.flow.Flow

class RemoteNewsRepositoryImp(
    private val newsApi: NewsApi
): RemoteNewsRepository {
    override fun getTopHeadLine(): Flow<PagingData<Article>> {
        return  Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                GetHeadLinePagingSource(
                    newsApi =newsApi,
                )
            }
        ).flow
    }

    override fun getSearchResult(search: String): Flow<PagingData<Article>> {
        return  Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi =newsApi,
                    search = search
                )
            }
        ).flow
    }
}
package com.example.newsapp.data.remot.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remot.Article
import com.example.newsapp.data.remot.NewPagingSource
import com.example.newsapp.data.remot.dao.NewsApi
import com.example.newsapp.domain.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImp(
    private val newsApi: NewsApi
): NewsRepository {
    override fun getTopHeadLine(): Flow<PagingData<Article>> {
        return  Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NewPagingSource(
                    newsApi =newsApi,
                )
            }
        ).flow
    }
}
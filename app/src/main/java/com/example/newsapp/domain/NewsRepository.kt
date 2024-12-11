package com.example.newsapp.domain

import androidx.paging.PagingData
import com.example.newsapp.data.remot.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getTopHeadLine(): Flow<PagingData<Article>>
    fun getSearchResult(search: String): Flow<PagingData<Article>>
}
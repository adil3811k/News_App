package com.example.newsapp.domain

import com.example.newsapp.data.local.SearchHistory
import com.example.newsapp.data.remot.Article
import kotlinx.coroutines.flow.Flow

interface LocalNewsRepository {
    suspend fun insertArticle(article: Article)
    suspend fun isArticleFavorite(title: String): Boolean
    suspend fun getArticle(title: String): Article
    suspend fun deleteArticle(article: Article)
    fun getAllFavorites(): Flow<List<Article>>


    fun gatAllSearchHistory(): Flow<List<SearchHistory>>
    suspend fun deleteHistory(id: Int)
    suspend fun addHistory(search: String)
}
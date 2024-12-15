package com.example.newsapp.domain

import com.example.newsapp.data.remot.Article

interface LocalNewsRepository {
    suspend fun insertArticle(article: Article)
    suspend fun isArticleFavorite(title: String): Boolean
    suspend fun getArticle(title: String): Article
    suspend fun deleteArticle(article: Article)
}
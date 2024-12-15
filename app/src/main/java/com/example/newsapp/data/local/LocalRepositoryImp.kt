package com.example.newsapp.data.local

import com.example.newsapp.data.remot.Article
import com.example.newsapp.domain.LocalNewsRepository

class LocalRepositoryImp(
    private val dao: RoomDao
) : LocalNewsRepository {
    override suspend fun insertArticle(article: Article) {
        dao.insertArticle(article)
    }

    override suspend fun isArticleFavorite(title: String): Boolean {
        val article = dao.getArticle(title)
        // Return true if it is have in favourite list
        return article!=null
    }

    override suspend fun getArticle(title: String): Article {
       return dao.getArticle(title)!!
    }

    override suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(article)
    }
}
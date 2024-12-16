package com.example.newsapp.data.local

import com.example.newsapp.data.remot.Article
import com.example.newsapp.domain.LocalNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

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

    override  fun getAllFavorites(): Flow<List<Article>> {
        return dao.getAllFavorites()
    }

    override fun gatAllSearchHistory(): Flow<List<SearchHistory>> {
        return dao.getAllSearchHistory()
    }

    override suspend fun deleteHistory(id: Int) {
        withContext(Dispatchers.IO){
            val search = dao.getOneHistory(id)
            dao.deleteSearch(search)
        }
    }

    override suspend fun addHistory(search: String) {
        dao.insertSearch(SearchHistory(search))
    }
}
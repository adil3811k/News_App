package com.example.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.newsapp.data.remot.Article
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RoomDao {
    @Upsert
    abstract suspend fun insertArticle(article: Article)
    
    @Query("""select * from favorites where title like :title limit 1""")
    abstract suspend fun getArticle(title: String) : Article?

    @Query("""select * from favorites""")
    abstract fun getAllFavorites(): Flow<List<Article>>

    @Delete
    abstract suspend fun deleteArticle(article: Article)
}
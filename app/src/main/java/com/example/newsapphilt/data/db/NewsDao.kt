package com.example.newsapphilt.data.db

import androidx.room.*
import com.example.newsapphilt.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(article: Article): Long

    @Delete
    suspend fun deleteNews(article: Article): Int

    @Query("select * from article_table")
    fun getAllSavedNews(): Flow<List<Article>>

    @Query("select * from article_table where title LIKE :query")
    fun searchSpecificNews(query: String): Flow<List<Article>>

}
package com.example.newsapphilt.data.repository.datasource

import com.example.newsapphilt.data.model.Article
import kotlinx.coroutines.flow.Flow

interface LocalDataStore {
    fun getNewsFromDB(): Flow<List<Article>>

    fun searchNewsFromDB(query: String): Flow<List<Article>>

    suspend fun addNewsToDB(article: Article): Long

    suspend fun deleteNewsFromDB(article: Article): Int

}
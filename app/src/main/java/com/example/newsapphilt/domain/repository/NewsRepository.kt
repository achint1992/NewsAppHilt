package com.example.newsapphilt.domain.repository

import com.example.newsapphilt.data.model.Article
import com.example.newsapphilt.data.model.NewsResponse
import com.example.newsapphilt.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsFromAPI(page:Int): Resource<NewsResponse>

    suspend fun searchNewsFromAPI(query: String,page:Int): Resource<NewsResponse>

    suspend fun deleteNewsFromDB(article: Article): Int

    suspend fun saveNewsToDB(article: Article): Long

    fun getSavedFromDB(): Flow<List<Article>>

    fun searchNewsFromDB(query: String): Flow<List<Article>>

}
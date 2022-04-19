package com.example.newsapphilt.data.repository.datasourceimpl

import com.example.newsapphilt.data.db.NewsDao
import com.example.newsapphilt.data.model.Article
import com.example.newsapphilt.data.repository.datasource.LocalDataStore
import kotlinx.coroutines.flow.Flow

class LocalDataStoreImpl(val newsDao: NewsDao) : LocalDataStore {
    override  fun getNewsFromDB(): Flow<List<Article>> {
        return newsDao.getAllSavedNews()
    }

    override  fun searchNewsFromDB(query: String): Flow<List<Article>> {
        return newsDao.searchSpecificNews(query)
    }

    override suspend fun addNewsToDB(article: Article):Long {
        return newsDao.addNews(article)
    }

    override suspend fun deleteNewsFromDB(article: Article):Int {
        return newsDao.deleteNews(article)
    }


}
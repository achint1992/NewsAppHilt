package com.example.newsapphilt.data.repository

import com.example.newsapphilt.data.model.Article
import com.example.newsapphilt.data.model.NewsResponse
import com.example.newsapphilt.data.repository.datasource.LocalDataStore
import com.example.newsapphilt.data.repository.datasource.RemoteDataStore
import com.example.newsapphilt.data.util.Resource
import com.example.newsapphilt.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(val remoteDataStore: RemoteDataStore, val localDataStore: LocalDataStore) :
    NewsRepository {
    override suspend fun getNewsFromAPI(page: Int): Resource<NewsResponse> {

        return responseToResource(remoteDataStore.getNewsFromAPI(page))
    }

    fun responseToResource(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())

    }

    override fun getSavedFromDB(): Flow<List<Article>> {
        return localDataStore.getNewsFromDB()
    }

    override suspend fun searchNewsFromAPI(query: String,page:Int): Resource<NewsResponse> {
        return responseToResource(remoteDataStore.searchNewsFromAPI(query,page))
    }

    override fun searchNewsFromDB(query: String): Flow<List<Article>> {
        return localDataStore.searchNewsFromDB(query)
    }

    override suspend fun saveNewsToDB(article: Article): Long {
        return localDataStore.addNewsToDB(article)
    }

    override suspend fun deleteNewsFromDB(article: Article): Int {
        return localDataStore.deleteNewsFromDB(article)
    }

}
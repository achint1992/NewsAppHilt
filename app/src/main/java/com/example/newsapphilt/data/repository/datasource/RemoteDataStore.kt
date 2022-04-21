package com.example.newsapphilt.data.repository.datasource

import com.example.newsapphilt.data.model.NewsResponse
import retrofit2.Response

interface RemoteDataStore {
    suspend fun getNewsFromAPI(page:Int): Response<NewsResponse>

    suspend fun searchNewsFromAPI(query: String,page:Int): Response<NewsResponse>
}
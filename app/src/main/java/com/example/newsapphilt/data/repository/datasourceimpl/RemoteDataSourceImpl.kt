package com.example.newsapphilt.data.repository.datasourceimpl

import com.example.newsapphilt.data.api.NewsService
import com.example.newsapphilt.data.model.Article
import com.example.newsapphilt.data.model.NewsResponse
import com.example.newsapphilt.data.repository.datasource.RemoteDataStore
import retrofit2.Response

class RemoteDataSourceImpl(val newsService: NewsService, val apiKey: String) : RemoteDataStore {
    override suspend fun getNewsFromAPI(page: Int): Response<NewsResponse> {
        return newsService.getPopularNews(apiKey, "in", page)
    }

    override suspend fun searchNewsFromAPI(query: String, page: Int): Response<NewsResponse> {
        return newsService.searchNews(apiKey, query, page)
    }

}
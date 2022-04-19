package com.example.newsapphilt.data.api

import com.example.newsapphilt.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {
    @GET("top-headlines")
    suspend fun getPopularNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("page") page: Int
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun searchNews(
        @Query("apiKey") apiKey: String,
        @Query("q") query: String,
        @Query("page") page: Int
    ): Response<NewsResponse>

}
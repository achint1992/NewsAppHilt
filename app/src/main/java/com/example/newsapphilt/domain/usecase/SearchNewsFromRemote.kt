package com.example.newsapphilt.domain.usecase

import com.example.newsapphilt.data.model.NewsResponse
import com.example.newsapphilt.data.util.Resource
import com.example.newsapphilt.domain.repository.NewsRepository

class SearchNewsFromRemote(val newsRepository: NewsRepository) {
    suspend fun execute(query: String, page: Int): Resource<NewsResponse> =
        newsRepository.searchNewsFromAPI(query, page)
}
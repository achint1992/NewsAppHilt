package com.example.newsapphilt.domain.usecase

import com.example.newsapphilt.data.model.Article
import com.example.newsapphilt.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchSavedNewsInDB(val newsRepository: NewsRepository) {
    suspend fun execute(query: String): Flow<List<Article>> = newsRepository.searchNewsFromDB(query)
}
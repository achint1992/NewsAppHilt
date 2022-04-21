package com.example.newsapphilt.domain.usecase

import com.example.newsapphilt.data.model.Article
import com.example.newsapphilt.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsFromDB(val newsRepository: NewsRepository) {
     fun execute(): Flow<List<Article>> = newsRepository.getSavedFromDB()
}
package com.example.newsapphilt.domain.usecase

import com.example.newsapphilt.data.model.Article
import com.example.newsapphilt.domain.repository.NewsRepository

class DeleteSavedNews(val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNewsFromDB(article)
}
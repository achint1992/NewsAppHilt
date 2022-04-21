package com.example.newsapphilt.domain.usecase

import com.example.newsapphilt.data.model.NewsResponse
import com.example.newsapphilt.data.util.Resource
import com.example.newsapphilt.domain.repository.NewsRepository

class GetNewsFromRemote(val newsRepository: NewsRepository) {
    suspend fun execute(page:Int): Resource<NewsResponse> = newsRepository.getNewsFromAPI(page)
}
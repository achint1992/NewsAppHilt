package com.example.newsapphilt.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapphilt.data.model.NewsResponse
import com.example.newsapphilt.data.util.Resource
import com.example.newsapphilt.domain.usecase.GetNewsFromRemote
import com.example.newsapphilt.domain.usecase.SearchNewsFromRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    val app: Application,
    val getNewsFromRemote: GetNewsFromRemote,
    val searchNewsFromRemote: SearchNewsFromRemote
) :
    AndroidViewModel(app) {
    val remoteNewsHeadLines: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchHeadlines: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()


    fun getNewsFromRemote(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        remoteNewsHeadLines.postValue(Resource.Loading())
        try {
            val apiResult = getNewsFromRemote.execute(page)
            remoteNewsHeadLines.postValue(apiResult)
        } catch (e: Exception) {
            e.printStackTrace()
            remoteNewsHeadLines.postValue(Resource.Error("Caught An Exception"))
        }
    }

    fun searchNewsFromRemote(query: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        searchHeadlines.postValue(Resource.Loading())
        try {
            val apiResult = searchNewsFromRemote.execute(query, page)
            searchHeadlines.postValue(apiResult)
        } catch (e: Exception) {
            e.printStackTrace()
            searchHeadlines.postValue(Resource.Error("Caught An Exception"))
        }
    }
}
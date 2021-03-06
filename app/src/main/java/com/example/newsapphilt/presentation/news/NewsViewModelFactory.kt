@file:Suppress("UNCHECKED_CAST")

package com.example.newsapphilt.presentation.news

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapphilt.domain.usecase.GetNewsFromRemote
import com.example.newsapphilt.domain.usecase.SearchNewsFromRemote
import java.lang.IllegalArgumentException

class NewsViewModelFactory(
    val app: Application,
    val getNewsFromRemote: GetNewsFromRemote,
    val searchNewsFromRemote: SearchNewsFromRemote
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(app, getNewsFromRemote, searchNewsFromRemote) as T
        }
        throw IllegalArgumentException("Unknown view Class")
    }
}
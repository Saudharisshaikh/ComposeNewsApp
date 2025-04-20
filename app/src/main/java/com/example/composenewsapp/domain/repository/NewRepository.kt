package com.example.composenewsapp.domain.repository

import androidx.paging.PagingData
import com.example.composenewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewRepository {

    fun getNews(source:List<String>):Flow<PagingData<Article>>
}
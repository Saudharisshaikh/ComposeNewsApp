package com.example.composenewsapp.domain.repository

import androidx.paging.PagingData
import com.example.composenewsapp.data.local.Article
import kotlinx.coroutines.flow.Flow

interface NewRepository {

    fun getNews(source:List<String>):Flow<PagingData<Article>>
    fun searchNews(searchNews:String, source:List<String>):Flow<PagingData<Article>>

}
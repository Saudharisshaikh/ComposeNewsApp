package com.example.composenewsapp.domain.repository

import androidx.paging.PagingData
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewRepository {

    fun getNews(source:List<String>):Flow<PagingData<Article>>
    fun searchNews(searchNews:String, source:List<String>):Flow<PagingData<Article>>
    fun upsertArticle(article: ArticlesEntity)
    fun deleteArticle(article: ArticlesEntity)
    fun selectArticles():Flow<List<ArticlesEntity>>
    fun selectArticle(url:String):ArticlesEntity?

}
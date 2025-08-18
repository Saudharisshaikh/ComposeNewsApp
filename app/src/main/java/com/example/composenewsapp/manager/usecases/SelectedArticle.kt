package com.example.composenewsapp.manager.usecases

import com.example.composenewsapp.data.local.ArticlesDao
import com.example.composenewsapp.data.local.ArticlesEntity
import kotlinx.coroutines.flow.Flow

class SelectedArticle(private val articlesDao: ArticlesDao) {

     operator fun invoke():Flow<List<ArticlesEntity>>{
        return articlesDao.getArticles()
    }
}
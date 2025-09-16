package com.example.composenewsapp.manager.usecases

import com.example.composenewsapp.data.local.ArticlesDao
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.domain.repository.NewRepository
import kotlinx.coroutines.flow.Flow

class SelectedArticle(
    private val
    newsRepository: NewRepository
) {

     operator fun invoke():Flow<List<ArticlesEntity>>{
         return newsRepository.selectArticles()
        //return articlesDao.getArticles()
    }
}
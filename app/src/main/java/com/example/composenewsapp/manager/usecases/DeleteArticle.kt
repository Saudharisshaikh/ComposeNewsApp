package com.example.composenewsapp.manager.usecases

import com.example.composenewsapp.data.local.ArticlesDao
import com.example.composenewsapp.data.local.ArticlesEntity

class DeleteArticle(private val articlesDao: ArticlesDao) {
    suspend operator fun invoke(articlesEntity: ArticlesEntity){
        articlesDao.delete(articlesEntity)
    }
}
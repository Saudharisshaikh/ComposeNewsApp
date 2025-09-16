package com.example.composenewsapp.manager.usecases.news

import com.example.composenewsapp.manager.usecases.DeleteArticle
import com.example.composenewsapp.manager.usecases.SelectArticle
import com.example.composenewsapp.manager.usecases.SelectedArticle
import com.example.composenewsapp.manager.usecases.UpsertArticle

data class NewsUseCases(
    val news: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectedArticle: SelectedArticle,
    val selectArticle: SelectArticle
)

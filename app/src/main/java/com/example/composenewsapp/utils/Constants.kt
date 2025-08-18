package com.example.e_pharmacycompose.utils

import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.domain.model.Article

object Constants {

    const val APP_SETTINGS = "appSettings"
    const val APP_ENTRY = "appEntry"
    const val API_KEY = "bdfc85a8461e4994913b9cadef541f77"
    const val BASE_URL = "https://newsapi.org/v2/"
    const val DATABASE_NAME = "article_db"

    fun convertToArticleEntity(article: Article):ArticlesEntity{
        return ArticlesEntity(author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            id = article.source.id,
            name = article.source.name,
            url = article.url,
            urlToImage = article.urlToImage,
            title = article.title)
    }
}
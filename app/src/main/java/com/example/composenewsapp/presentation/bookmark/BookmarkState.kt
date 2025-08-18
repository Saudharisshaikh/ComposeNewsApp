package com.example.composenewsapp.presentation.bookmark

import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.domain.model.Article

data class BookmarkState(
     val articleList:List<ArticlesEntity> = emptyList()
)
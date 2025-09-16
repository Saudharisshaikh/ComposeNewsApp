package com.example.composenewsapp.presentation.details

import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.manager.usecases.SelectArticle

sealed class DetailEvent {
    data class UpsertDeleteArticle(val article: ArticlesEntity):DetailEvent()
    object RemoveSideEffect:DetailEvent()
}
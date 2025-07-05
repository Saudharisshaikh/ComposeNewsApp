package com.example.composenewsapp.data.remote.dto

import com.example.composenewsapp.data.local.Article

data class NewResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
package com.example.composenewsapp.presentation.Search

import androidx.paging.PagingData
import com.example.composenewsapp.data.local.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
 val searchQuery: String = "",
 val articles : Flow<PagingData<Article>>? = null
)
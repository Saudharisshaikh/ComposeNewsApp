package com.example.composenewsapp.presentation.Search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String):SearchEvent()
    object searchNews:SearchEvent()
}
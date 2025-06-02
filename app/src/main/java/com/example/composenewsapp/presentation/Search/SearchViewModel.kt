package com.example.composenewsapp.presentation.Search


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.composenewsapp.manager.usecases.news.NewsUseCases
import com.example.composenewsapp.presentation.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
):ViewModel() {


    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(searchEvent: SearchEvent){
        when(searchEvent){
            is SearchEvent.UpdateSearchQuery ->{
            _state.value = state.value.copy(searchQuery = searchEvent.searchQuery)
            }
            is SearchEvent.searchNews ->{
               searchNews()
            }
        }
    }

    private fun searchNews() {
       val articles = newsUseCases.searchNews(
           searchQuery = state.value.searchQuery,
           sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
       ).cachedIn(viewModelScope)

        _state.value = _state.value.copy(articles =  articles)
    }
}
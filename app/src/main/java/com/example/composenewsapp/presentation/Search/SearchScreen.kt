package com.example.composenewsapp.presentation.Search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composenewsapp.presentation.common.ArticleList
import com.example.composenewsapp.presentation.common.SearchBar
import com.example.composenewsapp.presentation.navgraph.Route
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.MeddiumPadding1

@Composable
fun SearchScreen(state:SearchState, event:(SearchEvent) -> Unit,
                 navigate:(String)-> Unit
) {

    Column(modifier = Modifier.padding(top = MeddiumPadding1,
                                       start = MeddiumPadding1,
                                       end = MeddiumPadding1
    ).statusBarsPadding()
        .fillMaxSize()
    ) {

        SearchBar(text = state.searchQuery, readOnly = false,
            onValueChange = {event(SearchEvent.UpdateSearchQuery(it)) }
            , onSearch = {event(SearchEvent.searchNews)}
        )

        Spacer(modifier = Modifier.height(MeddiumPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles, onClick = {navigate(Route.DetailScreen.route)})
        }
    }

}
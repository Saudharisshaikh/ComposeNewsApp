package com.example.composenewsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composenewsapp.onboarding.OnboardingViewModel
import com.example.composenewsapp.presentation.Search.SearchScreen
import com.example.composenewsapp.presentation.Search.SearchViewModel
import com.example.composenewsapp.presentation.bookmark.BookmarkScreen
import com.example.composenewsapp.presentation.bookmark.BookmarkViewModel
import com.example.composenewsapp.presentation.home.HomeScreen
import com.example.composenewsapp.presentation.home.HomeViewModel
import com.example.e_pharmacycompose.presentation.onboarding.OnBoardingScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NavGraph(startDestination: String) {

    val navController = rememberNavController()

    NavHost(navController = navController,startDestination = startDestination){

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route

        ){

            composable(
                route = Route.OnBoardingScreen.route)
            {
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            composable(
                route = Route.NewsNavigatorScreen.route)
            {
//                val viewModel:HomeViewModel = hiltViewModel()
//                val articles = viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(article = articles, navigate = {})

//                val viewModel:SearchViewModel = hiltViewModel()
//                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})

                val viewModel:BookmarkViewModel= hiltViewModel()
                BookmarkScreen(bookmarkState = viewModel.state.value, navigate = {})




               // Text(text = "NewNavigator")
            }
        }


    }
}
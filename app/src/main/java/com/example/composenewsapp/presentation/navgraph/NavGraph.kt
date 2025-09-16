package com.example.composenewsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.news_navigator.components.NewsNavigator
import com.example.composenewsapp.onboarding.OnboardingViewModel

import com.example.composenewsapp.presentation.bookmark.BookmarkScreen
import com.example.composenewsapp.presentation.bookmark.BookmarkViewModel
import com.example.composenewsapp.presentation.common.EmptyScreen


import com.example.composenewsapp.presentation.details.DetailViewModel

import com.example.composenewsapp.presentation.details.NewsDetailsScreen

import com.example.composenewsapp.presentation.home.HomeScreen
import com.example.composenewsapp.presentation.home.HomeViewModel

import com.example.e_pharmacycompose.presentation.onboarding.OnBoardingScreen

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

              //  val viewModel:BookmarkViewModel= hiltViewModel()
              //  BookmarkScreen(bookmarkState = viewModel.state.value, navigateToDetailScreen = {})



//                  val viewModel:DetailViewModel = hiltViewModel()
//                  val state = viewModel::onEvent
                 // DetailsScreen(article = articlesEntity, event = viewModel::onEvent, navigateUp = {navController.navigateUp()})

                  NewsNavigator()


//                  val viewModel:DetailViewModel = hiltViewModel()
//                  val state = viewModel::onEvent
//                  NewsDetailsScreen(
//                      articlesEntity = articlesEntity,
//                      event = viewModel::onEvent,
//                      navigateUp = {navController.navigateUp()}
//                  )
            }
        }


    }
}
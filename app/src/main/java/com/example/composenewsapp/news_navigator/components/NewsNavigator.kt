package com.example.composenewsapp.news_navigator.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composenewsapp.R
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.presentation.Search.SearchScreen
import com.example.composenewsapp.presentation.Search.SearchViewModel
import com.example.composenewsapp.presentation.bookmark.BookmarkScreen
import com.example.composenewsapp.presentation.bookmark.BookmarkViewModel
import com.example.composenewsapp.presentation.details.DetailEvent

import com.example.composenewsapp.presentation.details.DetailViewModel
import com.example.composenewsapp.presentation.details.NewsDetailsScreen
import com.example.composenewsapp.presentation.home.HomeScreen
import com.example.composenewsapp.presentation.home.HomeViewModel
import com.example.composenewsapp.presentation.navgraph.Route
import com.example.composenewsapp.utils.UIComponent
import com.example.e_pharmacycompose.utils.Constants

@Composable
fun NewsNavigator() {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Explicitly manage selected item state
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    // Update selected item when route changes
    LaunchedEffect(currentRoute) {
        selectedItem = when (currentRoute) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    // Check if bottom navigation should be visible
    val isBottomVisible = remember(currentRoute) {
        currentRoute in listOf(
            Route.HomeScreen.route,
            Route.SearchScreen.route,
            Route.BookmarkScreen.route
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        // Update selected item immediately for better UX
                        selectedItem = index

                        val targetRoute = when (index) {
                            0 -> Route.HomeScreen.route
                            1 -> Route.SearchScreen.route
                            2 -> Route.BookmarkScreen.route
                            else -> Route.HomeScreen.route
                        }

                        navigateToTab(navController, targetRoute)
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearchScreen = {
                        selectedItem = 1
                        navigateToTab(navController, Route.SearchScreen.route)
                    },
                    navigateToDetailScreen = { article ->
                        navigateToDetailScreen(navController, article)
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetailScreen = { article ->
                        navigateToDetailScreen(navController, article)
                    }
                )
            }

            composable(route = Route.DetailScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(event = DetailEvent.RemoveSideEffect)
                }

                navController.previousBackStackEntry
                    ?.savedStateHandle?.get<ArticlesEntity?>("article")
                    ?.let { article ->
                        NewsDetailsScreen(
                            articlesEntity = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    bookmarkState = state,
                    navigateToDetailScreen = { article ->
                        navigateToDetailScreen(navController, article)
                    }
                )
            }
        }
    }
}

// Enhanced navigation function with logging for debugging
private fun navigateToTab(navController: NavController, route: String) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Log.d("Navigation", "Attempting to navigate from $currentRoute to $route")

    // Don't navigate if already on the target route
    if (currentRoute == route) {
        Log.d("Navigation", "Already on target route: $route")
        return
    }

    try {
        navController.navigate(route) {
            // Pop up to the home screen (start destination) and clear everything above it
            popUpTo(Route.HomeScreen.route) {
                // Only save state if we're not going to home
                saveState = route != Route.HomeScreen.route
                // Don't include the home screen itself when popping
                inclusive = false
            }

            // Avoid multiple copies of the same destination
            launchSingleTop = true

            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
        Log.d("Navigation", "Successfully navigated to $route")
    } catch (e: Exception) {
        Log.e("Navigation", "Failed to navigate to $route", e)
    }
}

private fun navigateToDetailScreen(navController: NavController, article: Article) {
    try {
        val articlesEntity = Constants.convertToArticleEntity(article = article)
        navController.currentBackStackEntry?.savedStateHandle?.set("article", articlesEntity)
        navController.navigate(route = Route.DetailScreen.route)
        Log.d("Navigation", "Navigated to detail screen")
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("Navigation", "navigateToDetailScreen error: ${e.message}")
    }
}


//@Composable
//fun NewsNavigator() {
//    val bottomNavigationItem = remember {
//        listOf(
//            BottomNavigationItem(icon = R.drawable.ic_home,"Home"),
//            BottomNavigationItem(icon = R.drawable.ic_search,"Search"),
//            BottomNavigationItem(icon = R.drawable.ic_bookmark,"Bookmark"),
//            )
//    }
//    val navController = rememberNavController()
//    val backStackState = navController.currentBackStackEntryAsState().value
//    var selectedItem by rememberSaveable {
//        mutableStateOf(0)
//    }
//    selectedItem = remember(key1 = backStackState) {
//        when(backStackState?.destination?.route){
//            Route.HomeScreen.route -> 0
//            Route.SearchScreen.route -> 1
//            Route.BookmarkScreen.route ->2
//            else -> 0
//        }
//    }
//
//    val isBottomVisible  = remember(key1 = backStackState) {
//        backStackState?.destination?.route == Route.HomeScreen.route ||
//        backStackState?.destination?.route == Route.SearchScreen.route ||
//        backStackState?.destination?.route == Route.BookmarkScreen.route
//    }
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            if (isBottomVisible){
//            NewsBottomNavigation(
//                items = bottomNavigationItem,
//                selected = selectedItem,
//                onItemClick = {
//                    index->
//                    when(index){
//                        0-> navigateToTab(navController = navController,
//                            Route.HomeScreen.route)
//                        1-> navigateToTab(navController = navController,
//                            Route.SearchScreen.route
//                            )
//                        2-> navigateToTab(navController = navController,
//                            Route.BookmarkScreen.route)
//                    }
//                }
//            )
//        }
//        }
//    ) {
//        val bottomPadding = it.calculateBottomPadding()
//        NavHost(navController = navController,
//            startDestination = Route.HomeScreen.route,
//            modifier = Modifier.padding(bottom = bottomPadding)) {
//
//            composable(route = Route.HomeScreen.route){ backStackEntry->
//                val viewModel: HomeViewModel = hiltViewModel()
//                val articles = viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(
//                    articles = articles,
//                    navigateToSearchScreen = {
//                    navigateToTab(
//                        navController = navController,
//                        route = Route.SearchScreen.route)
//                },
//                    navigateToDetailScreen ={
//                        article->
//                        navigateToDetailScreen(
//                            navController = navController,
//                            article = article)
//                    }
//                )
//            }
//            composable(route = Route.SearchScreen.route){
//                val viewModel:SearchViewModel = hiltViewModel()
//                val state = viewModel.state.value
//
//                SearchScreen(
//                    state = state,
//                    event = viewModel::onEvent,
//                    navigateToDetailScreen = { article->
//                        navigateToDetailScreen(
//                            navController = navController,
//                            article = article)}
//                )
//            }
//            composable(route = Route.DetailScreen.route){
//                val viewModel:DetailViewModel = hiltViewModel()
//                if(viewModel.sideEffect != null){
//                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
//                    viewModel.onEvent(event = DetailEvent.RemoveSideEffect)
//                }
//                navController.previousBackStackEntry
//                    ?.savedStateHandle?.get<ArticlesEntity?>("article")
//                    ?.let { article ->
//                        NewsDetailsScreen (
//                            articlesEntity = article,
//                            event = viewModel::onEvent,
//                            navigateUp = { navController.navigateUp() })
//
//
//                    }
//            }
//            composable(route = Route.BookmarkScreen.route){
//                val viewModel:BookmarkViewModel = hiltViewModel()
//                val state = viewModel.state.value
//                BookmarkScreen(
//                    bookmarkState = state,
//                    navigateToDetailScreen = { article->
//                        navigateToDetailScreen(
//                            navController = navController,
//                            article = article)}
//                )
//            }
//
//        }
//    }
//}
//
//private fun navigateToTab(navController: NavController, route: String) {
//    navController.navigate(route) {
//        navController.graph.startDestinationRoute?.let { screen_route ->
//            popUpTo(screen_route) {
//                saveState = true
//            }
//        }
//        launchSingleTop = true
//        restoreState = true
//    }
//}
//
//private fun navigateToDetailScreen(navController: NavController, article: Article){
//    try{
//
//        val articlesEntity = Constants.convertToArticleEntity(article = article)
//        navController?.currentBackStackEntry?.savedStateHandle?.set("article",articlesEntity)
//        navController.navigate(route = Route.DetailScreen.route)
//    }
//    catch (e:Exception){
//        e.printStackTrace()
//        Log.d("--error", "navigateToDetailScreen: "+e.message)
//    }
//
//}
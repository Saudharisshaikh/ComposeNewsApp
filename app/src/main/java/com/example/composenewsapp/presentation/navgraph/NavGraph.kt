package com.example.composenewsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composenewsapp.onboarding.OnboardingViewModel
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
                route = Route.NewsNavigatorScreen.route){
                Text(text = "NewNavigator")
            }
        }


    }
}
package com.example.composenewsapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.manager.usecases.AppEntryUserCases
import com.example.composenewsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUserCases: AppEntryUserCases
):ViewModel(){


    var splashCondition  by mutableStateOf(true)
        private set

    var startDestination  by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUserCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            Log.d("--sd:", ""+shouldStartFromHomeScreen)
            if(shouldStartFromHomeScreen){
                startDestination = Route.NewsNavigation.route
            }
            else{
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false

        }.launchIn(viewModelScope)
    }


}
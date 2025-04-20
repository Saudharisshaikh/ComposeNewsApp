package com.example.composenewsapp.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenewsapp.manager.usecases.AppEntryUserCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appEntryUserCases:
    AppEntryUserCases):ViewModel() {


        fun onEvent(event: OnboardingEvent){

            when(event){

                is OnboardingEvent.SaveAppEntry ->{
                    saveAppEntry()
                }
            }

        }

    private fun saveAppEntry() {


        viewModelScope.launch {
            appEntryUserCases.saveAppEntry()
        }
    }
}
package com.example.composenewsapp.manager.usecases

import com.example.composenewsapp.manager.LocalStorageManager

class SaveAppEntry(
    private val localStorageManager: LocalStorageManager
) {
    suspend  operator fun invoke (){
        localStorageManager.saveAppEntry()
    }
}
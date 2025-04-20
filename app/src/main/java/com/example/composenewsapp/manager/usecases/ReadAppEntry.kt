package com.example.composenewsapp.manager.usecases

import com.example.composenewsapp.manager.LocalStorageManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localStorageManager: LocalStorageManager
) {
     operator fun invoke():Flow<Boolean>{
      return  localStorageManager.readAppEntry()
    }
}
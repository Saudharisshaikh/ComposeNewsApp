package com.example.composenewsapp.manager

import kotlinx.coroutines.flow.Flow

interface LocalStorageManager {
    suspend fun saveAppEntry()

    fun readAppEntry():Flow<Boolean>
}
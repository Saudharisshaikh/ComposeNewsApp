package com.example.composenewsapp.data.manager

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.composenewsapp.manager.LocalStorageManager
import com.example.e_pharmacycompose.utils.Constants
import com.example.e_pharmacycompose.utils.Constants.APP_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalStorageManagerImp(private val context: Context) : LocalStorageManager {

    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings->
            settings[PreferencesKeys.APP_ENTRY] = true
            Log.d("--call:", "saveAppEntry: ")
        }

    }

    override fun readAppEntry(): Flow<Boolean> {

        return context.dataStore.data.map { preferences->
            preferences[PreferencesKeys.APP_ENTRY]?:false
        }
    }


}

private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(APP_SETTINGS)

private object PreferencesKeys{

    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}
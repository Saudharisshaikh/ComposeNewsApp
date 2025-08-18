package com.example.composenewsapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeNewApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: ComposeNewApp? = null
        fun getAppContext(): Context = instance!!.applicationContext
    }
}
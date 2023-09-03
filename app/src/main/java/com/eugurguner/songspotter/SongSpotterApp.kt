package com.eugurguner.songspotter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SongSpotterApp : Application() {

    companion object {
        lateinit var instance: SongSpotterApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
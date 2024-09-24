package com.salmanzach.deps

import android.app.Application
import com.salmanzach.deps.di.initKoin
import org.koin.android.ext.koin.androidContext

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@WeatherApp)
        }
    }
}
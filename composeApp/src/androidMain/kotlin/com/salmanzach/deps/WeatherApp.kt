package com.salmanzach.deps

import android.app.Application
import com.salmanzach.deps.domain.di.initKoin
import org.koin.android.ext.koin.androidContext

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@WeatherApp)
        }
    }
}
package com.salmanzach.deps.domain.di

import com.salmanzach.deps.BuildConfig
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule = module {
    single {
        createHttpClient(OkHttp.create())
    }
}

actual val apiKey: String
    get() = BuildConfig.API_KEY
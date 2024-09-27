package com.salmanzach.deps.domain.di

import com.salmanzach.deps.BuildConfig
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule = module {
    single {
        BuildConfig.API_KEY
    }
    single {
        createHttpClient(OkHttp.create())
    }

}
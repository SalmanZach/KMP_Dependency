package com.salmanzach.deps.domain.di

import com.salmanzach.deps.domain.network.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule = module {
    single {
        createHttpClient(OkHttp.create())
    }
}
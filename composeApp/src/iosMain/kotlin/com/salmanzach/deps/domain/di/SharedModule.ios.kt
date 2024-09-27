package com.salmanzach.deps.domain.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val platformModule = module {
    single {
        getKey()
    }
    single {
     createHttpClient(Darwin.create())
    }
 }

fun getKey():String {
    return "API_KEY"
}
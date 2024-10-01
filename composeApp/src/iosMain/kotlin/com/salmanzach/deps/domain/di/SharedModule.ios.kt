package com.salmanzach.deps.domain.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val platformModule = module {
    single {
     createHttpClient(Darwin.create())
    }
 }


actual val apiKey: String
    get() = "add_your_key_here"
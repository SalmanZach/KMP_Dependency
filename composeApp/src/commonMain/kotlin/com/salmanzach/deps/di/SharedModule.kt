package com.salmanzach.deps.di

import com.salmanzach.deps.repo.WeatherRepository
import com.salmanzach.deps.repo.WeatherRepositoryImp
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(::WeatherRepositoryImp).bind<WeatherRepository>()
}

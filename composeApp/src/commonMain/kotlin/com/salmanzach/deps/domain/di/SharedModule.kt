package com.salmanzach.deps.domain.di

import com.salmanzach.deps.domain.network.WeatherServiceClient
import com.salmanzach.deps.repo.WeatherRepository
import com.salmanzach.deps.repo.WeatherRepositoryImp
import com.salmanzach.deps.viewModel.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

expect val apiKey: String

val sharedModule = module {
    singleOf(::WeatherServiceClient)
    singleOf(::WeatherRepositoryImp).bind<WeatherRepository>()
    viewModelOf(::HomeViewModel)
}

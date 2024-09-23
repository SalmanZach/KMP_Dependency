package com.salmanzach.deps.repo

import com.salmanzach.deps.network.WeatherService

interface WeatherRepository {
    fun getCurrentWeather() : String
}


class  WeatherRepositoryImp(
    private val service: WeatherService
) : WeatherRepository {

    override fun getCurrentWeather(): String {
        return service.getWeather()
    }

}
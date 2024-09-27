package com.salmanzach.deps.repo

import com.salmanzach.deps.domain.network.CurrentWeatherResponse
import com.salmanzach.deps.domain.network.WeatherServiceClient
import com.salmanzach.deps.util.NetworkError
import com.salmanzach.deps.util.Result

interface WeatherRepository {
    suspend fun getCurrentWeather() : Result<CurrentWeatherResponse, NetworkError>
}


class  WeatherRepositoryImp(
    private val service: WeatherServiceClient
) : WeatherRepository {

    override suspend fun getCurrentWeather(): Result<CurrentWeatherResponse, NetworkError> {
        return service.getCurrentWeather(26.899590834180884, 75.80793681260732 )
    }

}
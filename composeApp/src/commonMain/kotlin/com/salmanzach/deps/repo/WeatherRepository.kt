package com.salmanzach.deps.repo

import com.salmanzach.deps.domain.network.WeatherServiceClient
import com.salmanzach.deps.util.NetworkError
import com.salmanzach.deps.util.Result

interface WeatherRepository {
    suspend fun getCurrentWeather(apiKey:String) : Result<String, NetworkError>
}


class  WeatherRepositoryImp(
    private val service: WeatherServiceClient
) : WeatherRepository {

    override suspend fun getCurrentWeather(apiKey:String): Result<String, NetworkError> {
        return service.getCurrentWeather(
            lat = 33.44,
            lon = -94.04,
            appId =  "add your key"
        )
    }

}
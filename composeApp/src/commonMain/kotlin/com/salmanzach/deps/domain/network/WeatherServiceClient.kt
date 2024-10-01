package com.salmanzach.deps.domain.network

import com.salmanzach.deps.domain.Constants.BASE_URL
import com.salmanzach.deps.domain.di.apiKey
import com.salmanzach.deps.util.NetworkError
import com.salmanzach.deps.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class WeatherServiceClient(
    private val httpClient: HttpClient
) {


    suspend fun getCurrentWeather(lat:Double,lon:Double) : Result<CurrentWeatherResponse,NetworkError>{
        val response = try {
            httpClient.get(
                urlString = BASE_URL
            ) {
                parameter("lat", lat)
                parameter("lon", lon)
                parameter("appid", apiKey)
            }
        } catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when(response.status.value) {
            in 200..299 -> {
                val body = response.body<CurrentWeatherResponse>()
                Result.Success(body)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

}
package com.salmanzach.deps.domain.network

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(
    val result :String,
    val current : CurrentData
)


@Serializable
data class CurrentData(
    val dt :Long,
    val sunrise :Long,
    val sunset :Long,
    val temp :Long,
    val humidity :Long,
)
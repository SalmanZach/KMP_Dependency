package com.salmanzach.deps.viewModel

import androidx.lifecycle.ViewModel
import com.salmanzach.deps.repo.WeatherRepository

class HomeViewModel(
    private val repository: WeatherRepository
) :ViewModel() {

    fun getCurrentWeather() : String = repository.getCurrentWeather()

}
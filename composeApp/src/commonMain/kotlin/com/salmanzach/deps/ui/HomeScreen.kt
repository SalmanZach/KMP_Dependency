package com.salmanzach.deps.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.salmanzach.deps.domain.network.CurrentWeatherResponse
import com.salmanzach.deps.util.onError
import com.salmanzach.deps.util.onSuccess
import com.salmanzach.deps.viewModel.HomeViewModel
import kmpdependencies.composeapp.generated.resources.Res
import kmpdependencies.composeapp.generated.resources.app_name
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen() {
    MaterialTheme {
        KoinContext {
            val viewModel = koinViewModel<HomeViewModel>()
            WeatherScreen(viewModel)
        }
    }
}


@Composable
fun WeatherScreen(viewModel: HomeViewModel) {
    val data = remember { mutableStateOf<CurrentWeatherResponse?>(null) }
    val scope = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(true) }

    // Fetch weather data using the ViewModel
    LaunchedEffect(Unit) {
        scope.launch {
            val result = viewModel.getCurrentWeather()
            result.onError {
                isLoading.value = false
            }

            result.onSuccess {
                data.value = it
                isLoading.value = false
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = MaterialTheme.typography.h6,
                    text = stringResource(Res.string.app_name)
                )
            }
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Show loading indicator while data is being fetched
            if (isLoading.value) {
                CircularProgressIndicator()
            }

            // Show weather data in a table if available
            data.value?.let { weather ->
                WeatherInfoTable(weather)
            }
        }
    }
}

@Composable
fun WeatherInfoTable(weather: CurrentWeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Weather Info as rows in a table
        WeatherInfoCard(label = "Country", value = weather.sys.country)
        WeatherInfoCard(label = "Temperature", value = "${weather.main.temp} K")
        WeatherInfoCard(label = "Feels Like", value = "${weather.main.feelsLike} K")
        WeatherInfoCard(label = "Humidity", value = "${weather.main.humidity}%")
        WeatherInfoCard(label = "Pressure", value = "${weather.main.pressure} hPa")
        WeatherInfoCard(label = "Wind Speed", value = "${weather.wind.speed} m/s")
        WeatherInfoCard(label = "Clouds", value = "${weather.clouds.all}%")
        WeatherInfoCard(label = "Weather", value = weather.weather.firstOrNull()?.description ?: "N/A")
    }
}

@Composable
fun WeatherInfoCard(label: String, value: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colors.primary.copy(alpha = 0.5f),
                            MaterialTheme.colors.primaryVariant.copy(alpha = 0.9f)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}
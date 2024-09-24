package com.salmanzach.deps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salmanzach.deps.viewModel.HomeViewModel
import kmpdependencies.composeapp.generated.resources.Res
import kmpdependencies.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen() {
    MaterialTheme {
        KoinContext {
            val viewModel = koinViewModel<HomeViewModel>()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            style = MaterialTheme.typography.body1,
                            text = "Open Weather"
                        )
                    }
                }
            ) { paddingValues ->

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = viewModel.getCurrentWeather()
                    )
                }
            }
        }
    }
}
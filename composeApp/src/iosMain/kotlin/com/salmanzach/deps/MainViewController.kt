package com.salmanzach.deps

import androidx.compose.ui.window.ComposeUIViewController
import com.salmanzach.deps.domain.di.initKoin
import com.salmanzach.deps.ui.HomeScreen

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { HomeScreen() }
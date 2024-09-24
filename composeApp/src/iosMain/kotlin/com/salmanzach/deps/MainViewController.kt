package com.salmanzach.deps

import androidx.compose.ui.window.ComposeUIViewController
import com.salmanzach.deps.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { HomeScreen() }
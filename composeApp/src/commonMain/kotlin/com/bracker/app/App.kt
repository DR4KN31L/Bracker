package com.bracker.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import shared.ui.screen.LoginScreen
import shared.ui.screen.SplashScreen

@Composable
fun App() {
    var isSplashScreenVisible by remember { mutableStateOf(true) }

    MaterialTheme {
        if (isSplashScreenVisible) {
            SplashScreen(onTimeout = { isSplashScreenVisible = false })
        } else {
            LoginScreen()
        }
    }
}
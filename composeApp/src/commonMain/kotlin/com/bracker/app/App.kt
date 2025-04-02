package com.bracker.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext
import shared.routes.AppNavigation

@Composable
fun App() {
    MaterialTheme {
        KoinContext {
            AppNavigation()
        }
    }
}
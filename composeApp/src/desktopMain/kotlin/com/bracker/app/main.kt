package com.bracker.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.bracker.app.di.desktopDatabaseModule
import org.koin.core.context.startKoin
import shared.di.sharedModule

fun main() = application {

    startKoin {
        modules(desktopDatabaseModule, sharedModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Bracker",
    ) {
        App()
    }
}
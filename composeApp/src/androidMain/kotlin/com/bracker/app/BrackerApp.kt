package com.bracker.app

import android.app.Application
import com.bracker.app.di.androidDatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import shared.di.sharedModule

class BrackerApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@BrackerApp)
            modules(androidDatabaseModule, sharedModule)
        }
    }
}
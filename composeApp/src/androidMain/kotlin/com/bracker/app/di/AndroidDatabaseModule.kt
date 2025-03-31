package com.bracker.app.di

import androidx.room.RoomDatabase
import com.bracker.app.data.localDb.androidDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import shared.data.localDb.BrackerDB

val androidDatabaseModule = module {
    single<RoomDatabase.Builder<BrackerDB>>{ androidDatabaseBuilder(androidContext()) }
}
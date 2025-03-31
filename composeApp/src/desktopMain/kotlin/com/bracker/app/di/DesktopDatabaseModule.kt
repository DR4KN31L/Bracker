package com.bracker.app.di

import androidx.room.RoomDatabase
import com.bracker.app.data.localDb.desktopDatabaseBuilder
import org.koin.dsl.module
import shared.data.localDb.BrackerDB


val desktopDatabaseModule = module {
    single<RoomDatabase.Builder<BrackerDB>> { desktopDatabaseBuilder() }
}
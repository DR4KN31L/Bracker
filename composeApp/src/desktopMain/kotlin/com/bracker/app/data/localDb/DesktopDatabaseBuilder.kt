package com.bracker.app.data.localDb

import androidx.room.Room
import androidx.room.RoomDatabase
import shared.data.localDb.BrackerDB
import shared.data.localDb.DATABASE_NAME
import java.io.File

fun desktopDatabaseBuilder(): RoomDatabase.Builder<BrackerDB> {
    val dbFile = File(System.getProperty("user.home"), ".brackerApp/$DATABASE_NAME")
    dbFile.parentFile?.mkdirs()
    return Room.databaseBuilder(
        dbFile.absolutePath
    )
}
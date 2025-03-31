package com.bracker.app.data.localDb

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import shared.data.localDb.BrackerDB
import shared.data.localDb.DATABASE_NAME


fun androidDatabaseBuilder(context: Context): RoomDatabase.Builder<BrackerDB> {
    val dbFile = context.applicationContext.getDatabasePath(DATABASE_NAME)

    return Room.databaseBuilder(
        context,
        dbFile.absolutePath
    )
}
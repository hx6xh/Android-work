package com.example.hx

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [TLog::class], exportSchema = false)
abstract class TLogDatabase : RoomDatabase() {
    abstract fun TLogDao(): TLogDao
}

class DatabaseManager(private val appContext: Context) {
    val database =
        Room.databaseBuilder(
            appContext.applicationContext,
            TLogDatabase::class.java,
            "events_log_database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    val tLogDao: TLogDao
        get() = database.TLogDao()
    fun deleteLogEntry(logId:Long) {
        tLogDao.deleteLog(logId) }
}
package com.example.hx

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TLogDao {
    @Insert
    fun insertLog(log: TLog)

    @Query("delete from events_log where id = :Id")
    fun deleteLog(Id: Long?)

    @Query("delete from events_log")
    fun deleteAllLogs()

    @Query("select * from events_log order by id asc")
    fun queryAllLogs():List<TLog>

    @Update
    fun updateLogs(log: TLog)
}

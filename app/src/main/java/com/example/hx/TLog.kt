package com.example.hx

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("events_log")
data class TLog(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "time")
    var time: String,

    @ColumnInfo(name = "event")
    var event: String
) {
    constructor(time: String, event: String) : this(0,time,event)
}
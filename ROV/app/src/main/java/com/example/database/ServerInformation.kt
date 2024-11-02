package com.example.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ServerInformation(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo()
    val serverIpAddress:String,

    @ColumnInfo()
    val liefPort:String,

    @ColumnInfo()
    val joystickPort:String,
)

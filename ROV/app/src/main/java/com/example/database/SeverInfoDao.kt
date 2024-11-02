package com.example.database

import androidx.room.*

@Dao
interface SeverInfoDao {
    @Insert
    fun insertInfo(serverInformation: ServerInformation): Long

    @Update
    fun updateInfo(newServerInformation: ServerInformation)

    @Query("select * from ServerInformation")
    fun loadAllInfo(): List<ServerInformation>

    @Delete
    fun deleteInfo(serverInformation: ServerInformation)

    @Query("select * from ServerInformation where id = 1")
    fun upDataInfo(): ServerInformation




}
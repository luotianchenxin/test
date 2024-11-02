package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1, entities = [ServerInformation::class], exportSchema = false)
abstract class ServerInfoDatabase :RoomDatabase() {


    abstract fun serverInfoDao():SeverInfoDao
    companion object{
        private var instance:ServerInfoDatabase? = null

    @Synchronized
    fun getDatabase(context: Context): ServerInfoDatabase {
        instance?.let {
            return it
        }
        return Room.databaseBuilder(context.applicationContext,
            ServerInfoDatabase::class.java, "app_database")
            .build().apply {
                instance = this
            }
    }
}

}
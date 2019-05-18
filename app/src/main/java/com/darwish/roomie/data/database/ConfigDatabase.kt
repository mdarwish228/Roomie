package com.darwish.roomie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darwish.roomie.data.config.Config
import com.darwish.roomie.data.config.ConfigDao

@Database(entities = [Config::class], version = 1)
abstract class ConfigDatabase : RoomDatabase() {
    abstract fun configDao(): ConfigDao
}
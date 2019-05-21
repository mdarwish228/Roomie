package com.darwish.roomie.data.config

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ConfigDao {
    @Query("SELECT * FROM config WHERE config.`key` = :key LIMIT 1")
    fun get(key:  String): Config?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(config: Config)
}
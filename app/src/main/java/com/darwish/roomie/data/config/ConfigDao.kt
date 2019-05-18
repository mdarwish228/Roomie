package com.darwish.roomie.data.config

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.darwish.roomie.data.config.Config

@Dao
interface ConfigDao {
    @Query("SELECT * FROM config WHERE config.`key` = :key LIMIT 1")
    fun get(key:  String): Config?

    @Insert
    fun insert(config: Config)
}
package com.darwish.roomie.data.config

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Config(
    @PrimaryKey val key: String,
    @ColumnInfo val value: String
)
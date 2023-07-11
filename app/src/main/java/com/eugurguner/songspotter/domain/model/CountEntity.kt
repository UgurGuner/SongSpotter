package com.eugurguner.songspotter.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countEntity")
data class CountEntity(
    @PrimaryKey val id: Int = -1,
    @ColumnInfo(name = "count") val count: Int
)
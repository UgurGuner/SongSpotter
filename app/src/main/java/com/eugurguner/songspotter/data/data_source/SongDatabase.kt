package com.eugurguner.songspotter.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eugurguner.songspotter.domain.model.CountEntity
import com.eugurguner.songspotter.domain.model.Song

@Database(
    entities = [Song::class, CountEntity::class],
    version = 1
)
abstract class SongDatabase : RoomDatabase() {

    abstract val songDao: SongDao

    companion object {
        const val DATABASE_NAME = "songSpotter_db"
    }
}
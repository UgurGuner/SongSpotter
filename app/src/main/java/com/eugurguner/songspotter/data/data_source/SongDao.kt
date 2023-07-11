package com.eugurguner.songspotter.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eugurguner.songspotter.domain.model.CountEntity
import com.eugurguner.songspotter.domain.model.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {

    @Query("SELECT * FROM song")
    fun getSongs(): Flow<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCount(count: CountEntity)

    @Query("SELECT * FROM countEntity WHERE id = -1")
    suspend fun getCountEntity(): CountEntity?

    @Query("SELECT * FROM song WHERE id = :id")
    suspend fun getSongById(id: Int): Song?

    @Query("DELETE FROM song")
    suspend fun deleteSongs()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(list: List<Song>)

}
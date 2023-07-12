package com.eugurguner.songspotter.domain.repository

import com.eugurguner.songspotter.domain.model.CountEntity
import com.eugurguner.songspotter.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {

    fun getSongs(): Flow<List<Song>>

    suspend fun getSongById(id: Int): Song?

    suspend fun insertSongs(list: List<Song>)

    suspend fun deleteSongs()

    suspend fun deleteSong(song: Song)

    suspend fun getCountEntity(): CountEntity?

    suspend fun insertCountOfSongs(countEntity: CountEntity)

    fun fetchSongs(
        searchTerm: String,
        limit: Int,
        offset: Int,
        onSuccess: (List<Song>, Int) -> Unit,
        onError: (String) -> Unit
    )

    fun fetchLocalData(
        onSuccess: (List<Song>, Int) -> Unit
    )

}
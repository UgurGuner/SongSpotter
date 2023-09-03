package com.eugurguner.songspotter.data.repository

import com.eugurguner.songspotter.domain.model.CountEntity
import com.eugurguner.songspotter.domain.model.Song
import com.eugurguner.songspotter.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : SongRepository {

    private val songs: ArrayList<Song> = arrayListOf()
    private var countEntity: CountEntity = CountEntity(count = 0)

    override fun getSongs(): Flow<List<Song>> {
        return flow {
            emit(songs)
        }
    }

    override suspend fun getSongById(id: Int): Song? {
        return songs.find { it.id == id }
    }

    override suspend fun insertSongs(list: List<Song>) {
        songs.addAll(list)
    }

    override suspend fun deleteSongs() {
        songs.clear()
    }

    override suspend fun deleteSong(song: Song) {
        songs.remove(song)
    }

    override suspend fun getCountEntity(): CountEntity {
        return countEntity
    }

    override suspend fun insertCountOfSongs(countEntity: CountEntity) {
        this.countEntity = countEntity
    }

    override fun fetchSongs(
        searchTerm: String,
        limit: Int,
        offset: Int,
        onSuccess: (List<Song>, Int) -> Unit,
        onError: (String) -> Unit
    ) {
        if (songs.isNotEmpty()) {
            onSuccess(songs, countEntity.count)
        } else {
            onError("An error occurred")
        }
    }

    override fun fetchLocalData(onSuccess: (List<Song>, Int) -> Unit) {
        onSuccess(songs, countEntity.count)
    }
}
package com.eugurguner.songspotter.domain.use_case

import com.eugurguner.songspotter.domain.model.Song
import com.eugurguner.songspotter.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class GetSongsUseCase(
    private val songRepository: SongRepository
) {
    operator fun invoke(): Flow<List<Song>> {
        return songRepository.getSongs()
    }
}
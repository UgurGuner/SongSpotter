package com.eugurguner.songspotter.domain.use_case

import com.eugurguner.songspotter.domain.repository.SongRepository

class FetchSongsUseCase(
    private val songRepository: SongRepository
)
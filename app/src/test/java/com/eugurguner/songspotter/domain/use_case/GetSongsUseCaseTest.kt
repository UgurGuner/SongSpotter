package com.eugurguner.songspotter.domain.use_case

import com.eugurguner.songspotter.data.repository.FakeRepository
import com.eugurguner.songspotter.domain.model.Song
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSongsUseCaseTest {

    private lateinit var fakeRepository: FakeRepository
    private lateinit var getSongsUseCase: GetSongsUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getSongsUseCase = GetSongsUseCase(fakeRepository)

        val songsToInsert = mutableListOf<Song>()
        ('a'..'z').forEachIndexed { index, c ->
            songsToInsert.add(
                Song(
                    wrapperType = c.toString(),
                    kind = c.toString(),
                    collectionId = index,
                    trackId = index,
                    artistName = c.toString(),
                    collectionName = c.toString(),
                    trackName = c.toString(),
                    collectionCensoredName = c.toString(),
                    trackCensoredName = c.toString(),
                    collectionArtistId = c.toString(),
                    collectionArtistViewUrl = c.toString(),
                    collectionViewUrl = c.toString(),
                    trackViewUrl = c.toString(),
                    previewUrl = c.toString(),
                    artworkUrl30 = c.toString(),
                    artworkUrl60 = c.toString(),
                    artworkUrl100 = c.toString(),
                    collectionPrice = 0.0,
                    trackPrice = 0.0,
                    trackRentalPrice = 0.0,
                    collectionHdPrice = 0.0,
                    trackHdPrice = 0.0,
                    trackHdRentalPrice = 0.0,
                    releaseDate = c.toString(),
                    collectionExplicitness = c.toString(),
                    trackExplicitness = c.toString(),
                    discCount = index,
                    discNumber = index,
                    trackCount = index,
                    trackNumber = index,
                    trackTimeMillis = index,
                    country = c.toString(),
                    currency = c.toString(),
                    primaryGenreName = c.toString(),
                    contentAdvisoryRating = c.toString(),
                    shortDescription = c.toString(),
                    longDescription = c.toString(),
                    hasITunesExtras = false
                )
            )
        }
        songsToInsert.shuffle()
        runBlocking {
            fakeRepository.insertSongs(songsToInsert)
        }
    }

    @Test
    fun `Songs If Inserted`() = runBlocking {
        val songs = getSongsUseCase.invoke().first()

        assertThat(songs.isNotEmpty()).isTrue()
    }
}
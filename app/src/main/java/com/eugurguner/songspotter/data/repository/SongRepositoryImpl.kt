package com.eugurguner.songspotter.data.repository

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.eugurguner.songspotter.data.data_source.SongDao
import com.eugurguner.songspotter.domain.model.CountEntity
import com.eugurguner.songspotter.domain.model.Song
import com.eugurguner.songspotter.domain.repository.SongRepository
import com.eugurguner.songspotter.domain.util.VolleySingleton
import kotlinx.coroutines.flow.Flow

class SongRepositoryImpl(
    private val songDao: SongDao
) : SongRepository {

    override fun getSongs(): Flow<List<Song>> {
        return songDao.getSongs()
    }

    override suspend fun getSongById(id: Int): Song? {
        return songDao.getSongById(id)
    }

    override suspend fun insertSongs(list: List<Song>) {
        return songDao.insertSongs(list)
    }

    override suspend fun deleteSong(song: Song) {
        return songDao.deleteSong(song)
    }

    override suspend fun deleteSongs() {
        return songDao.deleteSongs()
    }

    override suspend fun getCountEntity(): CountEntity? {
        return songDao.getCountEntity()
    }

    override suspend fun insertCountOfSongs(countEntity: CountEntity) {
        return songDao.insertCount(countEntity)
    }

    override fun fetchSongs(
        searchTerm: String,
        limit: Int,
        offset: Int,
        onSuccess: (List<Song>, Int) -> Unit,
        onError: (String) -> Unit
    ) {
        val url = "https://itunes.apple.com/search?term=${searchTerm.replace(" ", "+")}&limit=$limit&offset=$offset"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val songs = mutableListOf<Song>()
                val count = response.optInt("resultCount")
                val results = response.getJSONArray("results")
                for (i in 0 until results.length()) {
                    val songData = results.getJSONObject(i)
                    val song = Song(
                        wrapperType = songData.optString("wrapperType"),
                        kind = songData.optString("kind"),
                        collectionId = songData.optInt("collectionId"),
                        trackId = songData.optInt("trackId"),
                        artistName = songData.optString("artistName"),
                        collectionName = songData.optString("collectionName"),
                        trackName = songData.optString("trackName"),
                        collectionCensoredName = songData.optString("collectionCensoredName"),
                        trackCensoredName = songData.optString("trackCensoredName"),
                        collectionArtistId = songData.optString("collectionArtistId"),
                        collectionArtistViewUrl = songData.optString("collectionArtistViewUrl"),
                        collectionViewUrl = songData.optString("collectionViewUrl"),
                        trackViewUrl = songData.optString("trackViewUrl"),
                        previewUrl = songData.optString("previewUrl"),
                        artworkUrl30 = songData.optString("artworkUrl30"),
                        artworkUrl60 = songData.optString("artworkUrl60"),
                        artworkUrl100 = songData.optString("artworkUrl100"),
                        collectionPrice = songData.optDouble("collectionPrice"),
                        trackPrice = songData.optDouble("trackPrice"),
                        trackRentalPrice = songData.optDouble("trackRentalPrice"),
                        collectionHdPrice = songData.optDouble("collectionHdPrice"),
                        trackHdPrice = songData.optDouble("trackHdPrice"),
                        trackHdRentalPrice = songData.optDouble("trackHdRentalPrice"),
                        releaseDate = songData.optString("releaseDate"),
                        collectionExplicitness = songData.optString("collectionExplicitness"),
                        trackExplicitness = songData.optString("trackExplicitness"),
                        discCount = songData.optInt("discCount"),
                        discNumber = songData.optInt("discNumber"),
                        trackCount = songData.optInt("trackCount"),
                        trackNumber = songData.optInt("trackNumber"),
                        trackTimeMillis = songData.optInt("trackTimeMillis"),
                        country = songData.optString("country"),
                        currency = songData.optString("currency"),
                        primaryGenreName = songData.optString("primaryGenreName"),
                        contentAdvisoryRating = songData.optString("contentAdvisoryRating"),
                        shortDescription = songData.optString("shortDescription"),
                        longDescription = songData.optString("longDescription"),
                        hasITunesExtras = songData.optBoolean("hasITunesExtras")
                    )
                    songs.add(song)
                }
                onSuccess(songs, count)
            },
            { error ->
                onError(error.message ?: "An error occurred")
            }
        )

        VolleySingleton.getInstance().addToRequestQueue(request)
    }

    override fun fetchLocalData(onSuccess: (List<Song>, Int) -> Unit) {
        val url = "https://itunes.apple.com/search?term=jack+johnson"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val songs = mutableListOf<Song>()
                val count = response.optInt("resultCount")
                val results = response.getJSONArray("results")
                for (i in 0 until results.length()) {
                    val songData = results.getJSONObject(i)
                    val song = Song(
                        wrapperType = songData.optString("wrapperType"),
                        kind = songData.optString("kind"),
                        collectionId = songData.optInt("collectionId"),
                        trackId = songData.optInt("trackId"),
                        artistName = songData.optString("artistName"),
                        collectionName = songData.optString("collectionName"),
                        trackName = songData.optString("trackName"),
                        collectionCensoredName = songData.optString("collectionCensoredName"),
                        trackCensoredName = songData.optString("trackCensoredName"),
                        collectionArtistId = songData.optString("collectionArtistId"),
                        collectionArtistViewUrl = songData.optString("collectionArtistViewUrl"),
                        collectionViewUrl = songData.optString("collectionViewUrl"),
                        trackViewUrl = songData.optString("trackViewUrl"),
                        previewUrl = songData.optString("previewUrl"),
                        artworkUrl30 = songData.optString("artworkUrl30"),
                        artworkUrl60 = songData.optString("artworkUrl60"),
                        artworkUrl100 = songData.optString("artworkUrl100"),
                        collectionPrice = songData.optDouble("collectionPrice"),
                        trackPrice = songData.optDouble("trackPrice"),
                        trackRentalPrice = songData.optDouble("trackRentalPrice"),
                        collectionHdPrice = songData.optDouble("collectionHdPrice"),
                        trackHdPrice = songData.optDouble("trackHdPrice"),
                        trackHdRentalPrice = songData.optDouble("trackHdRentalPrice"),
                        releaseDate = songData.optString("releaseDate"),
                        collectionExplicitness = songData.optString("collectionExplicitness"),
                        trackExplicitness = songData.optString("trackExplicitness"),
                        discCount = songData.optInt("discCount"),
                        discNumber = songData.optInt("discNumber"),
                        trackCount = songData.optInt("trackCount"),
                        trackNumber = songData.optInt("trackNumber"),
                        trackTimeMillis = songData.optInt("trackTimeMillis"),
                        country = songData.optString("country"),
                        currency = songData.optString("currency"),
                        primaryGenreName = songData.optString("primaryGenreName"),
                        contentAdvisoryRating = songData.optString("contentAdvisoryRating"),
                        shortDescription = songData.optString("shortDescription"),
                        longDescription = songData.optString("longDescription"),
                        hasITunesExtras = songData.optBoolean("hasITunesExtras")
                    )
                    songs.add(song)
                }
                onSuccess(songs, count)
            },
            {}
        )

        VolleySingleton.getInstance().addToRequestQueue(request)
    }
}
package com.eugurguner.songspotter.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Song(
    var wrapperType: String?,
    var kind: String?,
    var collectionId: Int?,
    var trackId: Int?,
    var artistName: String?,
    var collectionName: String?,
    var trackName: String?,
    var collectionCensoredName: String?,
    var trackCensoredName: String?,
    var collectionArtistId: String?,
    var collectionArtistViewUrl: String?,
    var collectionViewUrl: String?,
    var trackViewUrl: String?,
    var previewUrl: String?,
    var artworkUrl30: String?,
    var artworkUrl60: String?,
    var artworkUrl100: String?,
    var collectionPrice: Double?,
    var trackPrice: Double?,
    var trackRentalPrice: Double?,
    var collectionHdPrice: Double?,
    var trackHdPrice: Double?,
    var trackHdRentalPrice: Double?,
    var releaseDate: String?,
    var collectionExplicitness: String?,
    var trackExplicitness: String?,
    var discCount: Int?,
    var discNumber: Int?,
    var trackCount: Int?,
    var trackNumber: Int?,
    var trackTimeMillis: Int?,
    var country: String?,
    var currency: String?,
    var primaryGenreName: String?,
    var contentAdvisoryRating: String?,
    var shortDescription: String?,
    var longDescription: String?,
    var hasITunesExtras: Boolean?,
    @PrimaryKey val id: Int? = null
) : Parcelable
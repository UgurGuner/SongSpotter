package com.eugurguner.songspotter.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.eugurguner.songspotter.databinding.ActivityArtistDetailBinding
import com.eugurguner.songspotter.domain.model.Song
import com.eugurguner.songspotter.presentation.util.formatPrice
import com.eugurguner.songspotter.presentation.util.getParcelableExtraProvider
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ActivityArtistDetail : AppCompatActivity() {

    private lateinit var binding: ActivityArtistDetailBinding
    private lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getParcelableExtraProvider<Song>("data") != null) {
            song = intent.getParcelableExtraProvider("data")!!
        } else {
            finish()
        }

        binding = ActivityArtistDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.lnrDetailArrow.setOnClickListener {
            finish()
        }

        updateUI()
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI() {
        if (!song.artworkUrl100.isNullOrEmpty()) {
            try {
                Glide.with(binding.root.context).load(song.artworkUrl100 ?: "")
                    .transform(CenterCrop(), RoundedCorners(160)).into(binding.imgArtist)
            } catch (_: Throwable) {
            }
        }

        binding.txtArtistName.text = song.artistName ?: ""

        binding.txtTrackName.text = song.trackName ?: ""

        binding.txtAlbumName.text = song.collectionName ?: ""

        binding.txtReleaseDate.text = "Release Date: ${dateFormat(song.releaseDate ?: "")}"

        binding.txtTrackPrice.text =
            "Track Price: ${song.currency ?: ""}${song.trackPrice?.formatPrice()}"

        binding.txtLongDescription.text = song.longDescription ?: ""
    }

    private fun dateFormat(day: String?): String? {
        return try {
            if (!day.isNullOrEmpty()) {
                val format1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale("tr"))
                val dt1: Date? = format1.parse(day)
                val format2: DateFormat = SimpleDateFormat("dd.MM.yy", Locale("tr"))
                format2.format(dt1 ?: "")
            } else {
                ""
            }
        } catch (_: Throwable) {
            ""
        }
    }
}
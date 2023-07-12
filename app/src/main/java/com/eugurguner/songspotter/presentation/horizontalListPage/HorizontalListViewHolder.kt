package com.eugurguner.songspotter.presentation.horizontalListPage

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.eugurguner.songspotter.databinding.HorizontalItemBinding
import com.eugurguner.songspotter.domain.model.Song
import com.eugurguner.songspotter.presentation.util.formatPrice
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HorizontalListViewHolder(val binding: HorizontalItemBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindItems(song: Song) {

        if (!song.artworkUrl100.isNullOrEmpty()) {

            try {
                Glide.with(binding.root.context).load(song.artworkUrl100 ?: "")
                    .transform(CenterCrop(), RoundedCorners(5)).into(binding.imgItem)
            } catch (_: Throwable) {
            }

        }

        binding.txtArtistName.text = song.artistName ?: ""

        binding.txtTrackName.text = song.trackName ?: ""

        binding.txtReleaseDate.text = "Release Date: ${dateFormat(song.releaseDate ?: "")}"

        binding.txtTrackPrice.text = "Track Price: ${song.currency ?: ""}${song.trackPrice?.formatPrice()}"

    }

    private fun dateFormat(day: String?): String? {

        return try {

            if (!day.isNullOrEmpty()) {
                val format1 = SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss'Z'", Locale("tr"))
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
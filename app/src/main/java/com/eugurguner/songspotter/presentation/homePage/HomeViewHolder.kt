package com.eugurguner.songspotter.presentation.homePage

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.eugurguner.songspotter.databinding.ArtistItemVerticalBinding
import com.eugurguner.songspotter.domain.model.Song

class HomeViewHolder(private val binding: ArtistItemVerticalBinding) : RecyclerView.ViewHolder(binding.root) {

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

    }


}
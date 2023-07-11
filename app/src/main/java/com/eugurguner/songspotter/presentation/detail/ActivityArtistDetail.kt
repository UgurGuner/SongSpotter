package com.eugurguner.songspotter.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.eugurguner.songspotter.databinding.ActivityArtistDetailBinding

class ActivityArtistDetail: AppCompatActivity() {

    private lateinit var binding: ActivityArtistDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArtistDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

    }

}
package com.eugurguner.songspotter.presentation.core

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.eugurguner.songspotter.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActivitySplash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(onBackPressedCallback)

        lifecycleScope.launch {
            delay(500)
            binding.txtSplashTitle.visibility = View.VISIBLE
            binding.imgSplash.visibility = View.VISIBLE
            delay(2000)
            goToHomePage()
        }
    }

    private fun goToHomePage() {
        Intent(this, ActivityMain::class.java).apply {
            startActivity(this)
        }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }
}
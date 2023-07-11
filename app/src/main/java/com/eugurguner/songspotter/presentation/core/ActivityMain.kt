package com.eugurguner.songspotter.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.eugurguner.songspotter.R
import com.eugurguner.songspotter.databinding.ActivityMainBinding
import com.eugurguner.songspotter.presentation.util.changeBarColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val coreViewModel: CoreViewModel by viewModels()
    private var selectedTab = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeBarColors(R.color.ss_background_black)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        window.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        )
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
        setBottomNavigationBar()
        fetchData()
    }

    private fun setBottomNavigationBar() {

        val navController = findNavController(R.id.navHostFragment)

        navController.enableOnBackPressed(false)

        binding.bottomNavigationView.setupWithNavController(navController)

        this.selectedTab = binding.bottomNavigationView.selectedItemId

        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            if (selectedTab == item.itemId) return@setOnItemSelectedListener false

            this.selectedTab = item.itemId

            when (item.itemId) {

                R.id.destination_item1 -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.destination_item2 -> {
                    navController.navigate(R.id.libraryFragment)
                    true
                }

                R.id.destination_item3 -> {
                    navController.navigate(R.id.fragmentGridList)
                    true
                }

                else -> {
                    false
                }

            }

        }

    }

    private fun fetchData() {
        coreViewModel.fetchLocalData()
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finishAffinity()
        }
    }

}
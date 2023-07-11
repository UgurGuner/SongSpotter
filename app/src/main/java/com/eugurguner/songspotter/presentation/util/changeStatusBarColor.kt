package com.eugurguner.songspotter.presentation.util

import android.app.Activity
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.eugurguner.songspotter.R

fun Activity.changeBarColors(color: Int) {

    window?.let {

        val wic = WindowInsetsControllerCompat(it, it.decorView)

        wic.isAppearanceLightStatusBars = true

        wic.isAppearanceLightNavigationBars = true

    }

    window.statusBarColor = ContextCompat.getColor(applicationContext, color)

    window.navigationBarColor = ContextCompat.getColor(applicationContext, color)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        window.navigationBarDividerColor = ContextCompat.getColor(applicationContext, R.color.ss_gray)
    }

}
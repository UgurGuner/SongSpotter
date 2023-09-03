package com.eugurguner.songspotter.presentation.util

fun Double.formatPrice(): String {
    return try {
        toInt().formatPrice()
    } catch (_: Throwable) {
        "0"
    }
}

fun Int.formatPrice(): String {
    return try {
        toString()
            .reversed()
            .chunked(3)
            .joinToString(".")
            .reversed()
    } catch (_: Throwable) {
        "0"
    }
}
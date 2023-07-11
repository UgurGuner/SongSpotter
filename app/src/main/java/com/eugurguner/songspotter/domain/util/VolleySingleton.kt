package com.eugurguner.songspotter.domain.util

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.eugurguner.songspotter.SongSpotterApp

class VolleySingleton private constructor() {

    companion object {
        @Volatile
        private var INSTANCE: VolleySingleton? = null

        fun getInstance(): VolleySingleton {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleySingleton().also {
                    INSTANCE = it
                }
            }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(SongSpotterApp.instance.applicationContext)
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }
}
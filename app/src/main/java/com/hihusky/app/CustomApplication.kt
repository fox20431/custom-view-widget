package com.hihusky.app

import android.app.Application
import com.hihusky.lib.util.ScalingUtil

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ScalingUtil(resources.displayMetrics)
    }
}
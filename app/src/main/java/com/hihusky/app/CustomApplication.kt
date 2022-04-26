package com.hihusky.app

import android.app.Application
import com.hihusky.lib.util.ScalingFactor

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val scalingFactor = ScalingFactor(resources.displayMetrics)
        scalingFactor.calcScale()
    }
}
package com.hihusky.app

import android.app.Application
import com.hihusky.lib.util.ScalingFactor
import com.hihusky.lib.util.ScalingFactor.Companion.targetDeviceDisplayMetrics

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        targetDeviceDisplayMetrics = resources.displayMetrics
        val scalingFactor = ScalingFactor()
        scalingFactor.calcScale()
    }
}
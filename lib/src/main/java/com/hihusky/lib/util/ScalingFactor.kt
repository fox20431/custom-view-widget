package com.hihusky.lib.util

import android.util.DisplayMetrics
import kotlin.math.min

class ScalingFactor(displayMetrics: DisplayMetrics) {

    companion object {
        var widthScale = 1F
        var heightScale = 1F
        var minScale = 1F
    }

    var targetDeviceDisplayMetrics = displayMetrics
    var referencedDeviceDisplayMetrics = DisplayMetrics()
    init {
        // The reference model is 'NEXUS 12' that display metrics is 1200 x 1920 pixels and 2 density.
        referencedDeviceDisplayMetrics.widthPixels = 1200
        referencedDeviceDisplayMetrics.heightPixels = 1920
        referencedDeviceDisplayMetrics.density = 2F
    }

    fun calcScale () {
        widthScale = (targetDeviceDisplayMetrics.widthPixels / targetDeviceDisplayMetrics.density) / (referencedDeviceDisplayMetrics.widthPixels / referencedDeviceDisplayMetrics.density)
        heightScale = (targetDeviceDisplayMetrics.heightPixels / targetDeviceDisplayMetrics.density) / (referencedDeviceDisplayMetrics.heightPixels / referencedDeviceDisplayMetrics.density)
        minScale = min(widthScale, heightScale)
    }
}

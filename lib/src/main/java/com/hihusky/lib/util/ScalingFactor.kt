package com.hihusky.lib.util

import android.util.DisplayMetrics
import kotlin.math.min

class ScalingFactor {

    var referencedDeviceDisplayMetrics = DisplayMetrics()
    var targetDeviceDisplayMetrics = DisplayMetrics()

    constructor(displayMetrics: DisplayMetrics) {
        targetDeviceDisplayMetrics = displayMetrics
    }

    init {
        referencedDeviceDisplayMetrics.density = 2F
        referencedDeviceDisplayMetrics.widthPixels = 1200
        referencedDeviceDisplayMetrics.heightPixels = 1920
    }

    companion object {
        var widthScale = 1F
        var heightScale = 1F
        var minScale = 1F

        /**
         * the reference model is 'NEXUS 12'
         * 1200 x 1920 pixels
         * 600 x 960 dp
         * density is 2
         */
    }

    fun calcScale () {
    }
}

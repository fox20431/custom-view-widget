package com.hihusky.lib.util

import android.util.DisplayMetrics
import kotlin.math.min

class ScalingUtil(displayMetrics: DisplayMetrics) {

    companion object {
        var widthScale = 1F
        var heightScale = 1F
        var minScale = 1F

        fun scaleHorizontal(value: Int): Int {
            return (value * widthScale + 0.5F).toInt()
        }

        fun scaleVertical(value: Int): Int {
            return (value * heightScale + 0.5F).toInt()
        }
    }

    init {
        // The reference model is 'NEXUS 12' that display metrics is 1200 x 1920 pixels and 2 density.
        val referencedDeviceDisplayMetrics = DisplayMetrics()
        referencedDeviceDisplayMetrics.widthPixels = 1200
        referencedDeviceDisplayMetrics.heightPixels = 1920
        referencedDeviceDisplayMetrics.density = 2F

        widthScale = (displayMetrics.widthPixels / displayMetrics.density) / (referencedDeviceDisplayMetrics.widthPixels / referencedDeviceDisplayMetrics.density)
        heightScale = (displayMetrics.heightPixels / displayMetrics.density) / (referencedDeviceDisplayMetrics.heightPixels / referencedDeviceDisplayMetrics.density)
        minScale = min(widthScale, heightScale)
    }

}

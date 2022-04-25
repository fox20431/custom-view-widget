package com.hihusky.lib.util

import android.content.Context
import kotlin.math.min

var widthScale = 1F
var heightScale = 1F
var minScale = 1F

var referencedDeviceWidth = 1200F
var referencedDeviceHeight = 1920F

/**
 * the reference model is 'NEXUS 12', its specification is '800x1280' dp.
 * @param width unit is 'dp'
 * @param height unit is 'dp'
 */
fun calcScale (width: Float, height: Float) {
    widthScale = width / referencedDeviceWidth
    heightScale = height / referencedDeviceHeight
    minScale = min(widthScale, heightScale)
}
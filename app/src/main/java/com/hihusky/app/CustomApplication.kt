package com.hihusky.app

import android.app.Application
import com.hihusky.lib.util.calcScale

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val width = resources.displayMetrics.widthPixels / resources.displayMetrics.density
        val height = resources.displayMetrics.heightPixels / resources.displayMetrics.density
        calcScale(width, height)
    }
}
package com.hihusky.lib.widget

import android.content.Context
import android.graphics.Canvas
import android.icu.util.Measure
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

/**
 * 参考[https://developer.android.com/reference/android/view/View]
 */
class CustomTextView: AppCompatTextView {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    companion object {
        var scale = 0.5
    }

    fun reflect() {
        val textViewClass = TextView::class.java
        // textViewClass.getDeclaredField()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val scaledWidthSize = (widthSize * scale + 0.5f).toInt()
        val scaledHeightSize = (heightSize * scale + 0.5f).toInt()
        val scaledWidthMeasureSpec = MeasureSpec.makeMeasureSpec(scaledWidthSize, widthMode)
        val scaledHeightMeasureSpec = MeasureSpec.makeMeasureSpec(scaledHeightSize, heightMode)
        super.onMeasure(scaledWidthMeasureSpec, scaledHeightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val scaledLeft = (left * scale + 0.5f).toInt()
        val scaledTop = (top * scale + 0.5f).toInt()
        val scaledRight = (right * scale + 0.5f).toInt()
        val scaledBottom = (bottom * scale + 0.5f).toInt()
        super.onLayout(changed, scaledLeft, scaledTop, scaledRight, scaledBottom)
    }

    override fun onDraw(canvas: Canvas?) {
        // canvas?.scale(scale.toFloat(), scale.toFloat())
        super.onDraw(canvas)
    }
}
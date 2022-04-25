package com.hihusky.lib.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.hihusky.lib.util.heightScale
import com.hihusky.lib.util.minScale

import com.hihusky.lib.util.widthScale

/**
 * 参考[https://developer.android.com/reference/android/view/View]
 */
class CustomTextView: AppCompatTextView {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    init {
        textSize *= minScale
    }

    /**
     * scale the font size if called the method modify size.
     */
    override fun setTextSize(unit: Int, size: Float) {
        val scaledSize = size * minScale
        super.setTextSize(unit, scaledSize)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val scaledWidthSize = (widthSize * widthScale + 0.5f).toInt()
        val scaledHeightSize = (heightSize * heightScale + 0.5f).toInt()
        val scaledWidthMeasureSpec = MeasureSpec.makeMeasureSpec(scaledWidthSize, widthMode)
        val scaledHeightMeasureSpec = MeasureSpec.makeMeasureSpec(scaledHeightSize, heightMode)
        super.onMeasure(scaledWidthMeasureSpec, scaledHeightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val scaledLeft = (left * widthScale + 0.5f).toInt()
        val scaledTop = (top * heightScale + 0.5f).toInt()
        val scaledRight = (right * widthScale + 0.5f).toInt()
        val scaledBottom = (bottom * heightScale + 0.5f).toInt()
        super.onLayout(changed, scaledLeft, scaledTop, scaledRight, scaledBottom)
    }

    override fun onDraw(canvas: Canvas?) {
        // canvas?.scale(scale.toFloat(), scale.toFloat())
        super.onDraw(canvas)
    }
}
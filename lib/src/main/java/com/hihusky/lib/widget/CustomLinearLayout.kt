package com.hihusky.lib.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.LinearLayout
import com.hihusky.lib.util.ScalingFactor.Companion.heightScale
import com.hihusky.lib.util.ScalingFactor.Companion.widthScale

class CustomLinearLayout : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context, attrs, defStyleAttr, defStyleRes
    )

    init {}

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

    // Don't use it, or the child layout will overflow the screen border.
    // override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    //     val scaledLeft = (left * widthScale + 0.5f).toInt()
    //     val scaledTop = (top * heightScale + 0.5f).toInt()
    //     val scaledRight = (right * widthScale + 0.5f).toInt()
    //     val scaledBottom = (bottom * heightScale + 0.5f).toInt()
    //     super.onLayout(changed, scaledLeft, scaledTop, scaledRight, scaledBottom)
    // }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}
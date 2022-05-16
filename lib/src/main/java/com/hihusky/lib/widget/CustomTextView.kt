package com.hihusky.lib.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.widget.AppCompatTextView
import com.hihusky.lib.util.ScalingUtil.Companion.heightScale
import com.hihusky.lib.util.ScalingUtil.Companion.minScale
import com.hihusky.lib.util.ScalingUtil.Companion.scaleHorizontal
import com.hihusky.lib.util.ScalingUtil.Companion.scaleVertical
import com.hihusky.lib.util.ScalingUtil.Companion.widthScale

/**
 * 参考[https://developer.android.com/reference/android/view/View]
 */
class CustomTextView: AppCompatTextView {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    // execute after primary constructor
    init {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        val scaledPaddingLeft = scaleHorizontal(paddingLeft)
        val scaledPaddingTop = scaleVertical(paddingTop)
        val scaledPaddingRight = scaleHorizontal(paddingRight)
        val scaledPaddingBottom = scaleVertical(paddingBottom)
        setPadding(scaledPaddingLeft, scaledPaddingTop, scaledPaddingRight, scaledPaddingBottom)
    }


    override fun setTextSize(unit: Int, size: Float) {
        val scaledSize = size * minScale
        super.setTextSize(unit, scaledSize)
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams?) {
        val marginLayoutParams = params as MarginLayoutParams
        width = scaleHorizontal(width)
        height = scaleVertical(height)
        marginLayoutParams.marginStart = (marginLayoutParams.marginStart * widthScale + 0.5F).toInt()
        marginLayoutParams.marginEnd = (marginLayoutParams.marginEnd * widthScale + 0.5F).toInt()
        marginLayoutParams.leftMargin = (marginLayoutParams.leftMargin * widthScale + 0.5F).toInt()
        marginLayoutParams.rightMargin *= (marginLayoutParams.rightMargin * widthScale + 0.5F).toInt()
        marginLayoutParams.topMargin *= (marginLayoutParams.topMargin * heightScale + 0.5F).toInt()
        marginLayoutParams.bottomMargin *= (marginLayoutParams.bottomMargin * heightScale + 0.5F).toInt()
        super.setLayoutParams(marginLayoutParams)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (layoutParams.width != -1 && layoutParams.width != -2) {
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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}
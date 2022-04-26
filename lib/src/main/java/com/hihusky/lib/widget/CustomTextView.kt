package com.hihusky.lib.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.hihusky.lib.util.ScalingFactor.Companion.heightScale
import com.hihusky.lib.util.ScalingFactor.Companion.minScale
import com.hihusky.lib.util.ScalingFactor.Companion.widthScale
import kotlin.math.min

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
        // Not working
        // TODO 这里可以看看怎么运行过程中如何更改宽高
        // width = (width * widthScale + 0.5F).toInt()
        // height = (height * heightScale).toInt()
        // val paddingLeft = (paddingLeft * heightScale + 0.5).toInt()
        // val paddingTop = (paddingTop * heightScale + 0.5).toInt()
        // val paddingRight = (paddingRight * heightScale + 0.5).toInt()
        // val paddingBottom = (paddingBottom * heightScale + 0.5).toInt()
        // setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
    }


    override fun setTextSize(size: Float) {
        val scaledSize = size * minScale
        super.setTextSize(scaledSize)
    }

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

    // override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
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
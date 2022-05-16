package com.hihusky.lib.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.LinearLayout
import com.hihusky.lib.util.ScalingUtil.Companion.heightScale
import com.hihusky.lib.util.ScalingUtil.Companion.widthScale

class CustomLinearLayout : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {}

    /**
     * 重写“测量时”方法。
     *
     * 初始化通过标签元素的属性能完成绝大多数呈现试图所需要的参数，
     * 但标签元素属性 `android:layout_width/layout_height` 与其他属性不同，
     * 它的值能接受除长度之外的 `match_parent` 和 `wrap_content` 参数，
     * 这两个参数是相对参数（相对父组件和子组件），这意味不能仅仅凭借着自身来决定这个参数，
     * 所以需要通过 `measure` 从 `view tree` 的根节点便测量边向下传值，通知子 view 自身情况。
     *
     * 这里注意 `match_parent` 、 `wrap_content` 以及 ConstraintLayout 中的 0dp 的情况，
     * 由于都是相对的参数，不应该缩放宽高。
     *
     * onMeasure 决定着视图的宽和高，因此需要在这里修改。
     */
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
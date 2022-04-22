package com.hihusky.lib.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.hihusky.lib.R

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        val string = typedArray.getString(R.styleable.CustomView_stringContent)
        val integer = typedArray.getInteger(R.styleable.CustomView_integerContent, -1)
        Log.d("test", """
            The view attrs
            R.styleable.CustomView_stringContent:   $string
            R.styleable.CustomView_integerContent:  $integer
        """.trimIndent())
        typedArray.recycle()
    }
}
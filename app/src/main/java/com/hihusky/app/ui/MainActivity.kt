package com.hihusky.app.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import com.hihusky.app.R
import com.hihusky.lib.widget.CustomLinearLayout
import com.hihusky.lib.widget.CustomTextView

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "mainTag"
    }

    private lateinit var customTextView: CustomTextView
    private lateinit var customLinearLayout: CustomLinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get view
        customTextView = findViewById(R.id.custom_text_view)
        customLinearLayout = findViewById(R.id.custom_linear_layout)

        // log the view info
        showViewInfo(customTextView)
    }

    /**
     * show view info
     * @param view this is shown.
     */
    private fun showViewInfo(view: View) {

        view.doOnPreDraw {
            val viewLocation = IntArray(2)
            it.getLocationOnScreen(viewLocation)

            Log.d(
                TAG, """
                    the metrics(unit: px):
                    width(without padding and margin): ${view.width}
                    height(without padding and margin): ${view.height}
                    padding(l t r b): ${view.paddingLeft} ${view.paddingTop} ${view.paddingRight} ${view.paddingBottom}
                    margin(l t r b): ${view.marginLeft} ${view.marginTop} ${view.marginRight} ${view.marginBottom}
                    the top coordinate: ${viewLocation[0]}
                    the left coordinate: ${viewLocation[1]}
                """.trimIndent()
            )

        }
    }

    private fun printViewHierarchy(vg: ViewGroup, prefix: String) {
        for (i in 0 until vg.childCount) {
            val v: View = vg.getChildAt(i)
            val desc = prefix + " | " + "[" + i + "/" + (vg.childCount - 1) + "] " + v.javaClass.simpleName + " " + v.id
            Log.v("x", desc)
            if (v is ViewGroup) {
                printViewHierarchy(v, desc)
            }
        }
    }
}

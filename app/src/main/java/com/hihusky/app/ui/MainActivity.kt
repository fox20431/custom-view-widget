package com.hihusky.app.ui

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
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

        customTextView = findViewById(R.id.custom_text_view)
        customLinearLayout = findViewById(R.id.custom_linear_layout)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val customLinearLayoutLocation = IntArray(2)
            customLinearLayout.getLocationOnScreen(customLinearLayoutLocation)
            val customTextViewLocation = IntArray(2)
            customLinearLayout.getLocationOnScreen(customTextViewLocation)

            customTextView.paddingBottom

            // // Padding
            // customTextView.setPadding()
            //
            // // Margin
            // val lp = LinearLayout.LayoutParams(
            //     LinearLayout.LayoutParams.WRAP_CONTENT,
            //     LinearLayout.LayoutParams.WRAP_CONTENT
            // )
            // lp.setMargins(10,10,10,10)
            // customTextView.layoutParams = lp

            Log.d(
                TAG, """
                    the metrics:
                    custom linear view:
                    width: ${customLinearLayout.width}
                    height: ${customLinearLayout.height}
                    the top coordinate: ${customLinearLayoutLocation[0]}
                    the left coordinate: ${customLinearLayoutLocation[1]}
                    
                    custom text view: 
                    width: ${customTextView.width}
                    height: ${customTextView.height}
                    text size: ${customTextView.textSize}
                    the top coordinate: ${customTextViewLocation[0]}
                    the left coordinate: ${customTextViewLocation[1]}
                """.trimIndent()
            )

        }
    }

}
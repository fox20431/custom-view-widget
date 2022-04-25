package com.hihusky.app

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hihusky.lib.widget.CustomTextView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var customTextView: CustomTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customTextView = findViewById(R.id.custom_text_view)
    }
}
package com.paul9834.mvc.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paul9834.mvc.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

    }
}

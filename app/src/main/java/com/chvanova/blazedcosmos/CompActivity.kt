package com.chvanova.blazedcosmos

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class CompActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val decorView = window.decorView
        val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        decorView.systemUiVisibility = uiOptions
        val handler = Handler()
        val hideSystemUI = Runnable { decorView.systemUiVisibility = uiOptions }

        decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // The system bars are visible. Schedule a task to hide the navigation and status bar after a few seconds.
                handler.postDelayed(hideSystemUI, 5000)
            }
        }
    }
}
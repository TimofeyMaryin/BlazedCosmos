package com.chvanova.blazedcosmos

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.RemoteConfigConstants
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var imageButtonSound: ImageButton
    private var isSoundOn = true
    private val mainViewModel by lazy { MainViewModel() }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.music)

        BranchManager.test(this)
        AppsflyerManager.launch(this)
        RemoteConfigManager.launch(this, mainViewModel)

        setContentView(R.layout.activity_main)
        val webView = findViewById<WebView>(R.id.web_view)
        mainViewModel.urlToWebView.observe(this) { value ->
            if (value.isNotEmpty() && value != "empty") {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                mediaPlayer.stop()
                webView.alpha = 1f
                webView.loadUrl(value)
            }
            if (value.isEmpty()) {

                mediaPlayer.isLooping = true
                mediaPlayer.start()

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

                val imageButtonSound = findViewById<ImageButton>(R.id.button_music)
                val imageButtonFriend = findViewById<ImageButton>(R.id.button_friend)
                val imageButtonComp = findViewById<ImageButton>(R.id.button_comp)



                imageButtonSound.setOnClickListener {
                    if (isSoundOn) {
                        // If the sound is on, turn it off
                        mediaPlayer.pause()
                        imageButtonSound.setImageResource(R.drawable.sound_off)
                    } else {
                        // If the sound is off, turn it on
                        mediaPlayer.start()
                        imageButtonSound.setImageResource(R.drawable.sound_on)
                    }
                    isSoundOn = !isSoundOn
                }

                imageButtonFriend.setOnClickListener {

                    val intent = Intent(this, FriendActivity::class.java)
                    intent.putExtra("Game type", "p2p")
                    startActivity(intent)
                }
                imageButtonComp.setOnClickListener {
                    val intent = Intent(this, FriendActivity::class.java)
                    intent.putExtra("Game type", "p2c")
                    startActivity(intent)
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        // Release the media player resources when the activity is destroyed
        mediaPlayer.release()
    }
}

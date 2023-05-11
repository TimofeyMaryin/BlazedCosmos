package com.chvanova.blazedcosmos

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object RemoteConfigManager {
    private val remoteConfig : FirebaseRemoteConfig by lazy {
        Firebase.remoteConfig
    }

    fun launch(activity: Activity, viewModel: MainViewModel){
        Log.e("REMOTE CONFIG", "Launch is started", )

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.deep_link_default_value)
        remoteConfig.fetchAndActivate().addOnCompleteListener(activity) { task ->
            if (task.isSuccessful){
                Log.e("REMOTE CONFIG", remoteConfig.getString("deep_link"), )
                viewModel.urlToWebView.value = remoteConfig.getString("deep_link")
                Log.e("REMOTE CONFIG", viewModel.urlToWebView.value ?: "NULL VALUE", )
            } else {
                Log.e("REMOTE CONFIG", "Failed to update Remote Config parameters", )
            }

        }

    }


}
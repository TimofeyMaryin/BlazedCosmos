package com.chvanova.blazedcosmos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    val urlToWebView: MutableLiveData<String> = MutableLiveData("empty")


    init {
        Log.e("MAIN VIEW MODEL", urlToWebView.value ?: "NULL VALUE", )
    }


}
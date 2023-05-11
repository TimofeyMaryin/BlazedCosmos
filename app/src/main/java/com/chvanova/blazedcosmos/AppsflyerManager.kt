package com.chvanova.blazedcosmos

import android.content.Context
import com.appsflyer.AppsFlyerLib

object AppsflyerManager {
    private const val devKey = "W9mYoWu9Q3unFmev7HQWWb"

    fun launch(context : Context) {
        val appsFlyer = AppsFlyerLib.getInstance()
        appsFlyer.setDebugLog(true)
        appsFlyer.setMinTimeBetweenSessions(0)
        AppsFlyerLib.getInstance().setAppInviteOneLink("H5hv")

        appsFlyer.init(devKey, null, context)
        appsFlyer.start(context)
    }

}
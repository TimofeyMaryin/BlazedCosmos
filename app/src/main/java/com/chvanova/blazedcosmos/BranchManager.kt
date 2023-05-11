package com.chvanova.blazedcosmos

import android.R
import android.content.Context
import android.util.Log
import android.webkit.WebView
import io.branch.indexing.BranchUniversalObject
import io.branch.referral.Branch
import io.branch.referral.Branch.BranchLinkCreateListener
import io.branch.referral.util.ContentMetadata
import io.branch.referral.util.LinkProperties


object BranchManager {


    fun launch(context : Context) {
        Branch.getAutoInstance(context)

        val advertisingId = BranchUniversalObject()
            .setCanonicalIdentifier("advertising_id")
            .setTitle("Deep link")
            .setContentDescription("This deep link about Advertising Id")

        val appsflyerId = BranchUniversalObject()
            .setCanonicalIdentifier("appsflyer_id")
            .setTitle("Deep link")
            .setContentDescription("This deep link about Appsflyer Id")

        val campaignId = BranchUniversalObject()
            .setCanonicalIdentifier("campaign_id")
            .setTitle("Deep link")
            .setContentDescription("This deep link about Campaign Id")

        val afChannel = BranchUniversalObject()
            .setCanonicalIdentifier("campaign_id")
            .setTitle("Deep link")
            .setContentDescription("This deep link about afChannel")

        val campaign = BranchUniversalObject()
            .setCanonicalIdentifier("campaign_id")
            .setTitle("Deep link")
            .setContentDescription("This deep link about Campaign")


        val lp: LinkProperties = LinkProperties()
            .setChannel("facebook")
            .setFeature("sharing")
            .addControlParameter("\$desktop_url", "http://example.com/item/12345")
            .addControlParameter("\$ios_url", "http://example.com/ios/item/12345")
            .addControlParameter("\$android_url", "http://example.com/android/item/12345")
            .addControlParameter("\$match_duration", "2000")

        advertisingId.generateShortUrl(context, lp, BranchLinkCreateListener { url, error ->
            if (error == null) {
                Log.e("BRANCH SDK", "got my Branch link to share: $url")
            } else {
                Log.e("BRANCH SDK", "ERROR WITH BRANCH ADVERTISING ")
            }
        })

        appsflyerId.generateShortUrl(context, lp, BranchLinkCreateListener { url, error ->
            if (error == null) {
                Log.e("BRANCH SDK", "got my Branch link to share: $url")
            } else {
                Log.e("BRANCH SDK", "ERROR WITH BRANCH APPSFLYER_ID ")
            }
        })

        campaignId.generateShortUrl(context, lp, BranchLinkCreateListener { url, error ->
            if (error == null) {
                Log.e("BRANCH SDK", "got my Branch link to share: $url")
            } else {
                Log.e("BRANCH SDK", "ERROR WITH BRANCH CAMPAIGN)ID ")
            }
        })

        afChannel.generateShortUrl(context, lp, BranchLinkCreateListener { url, error ->
            if (error == null) {
                Log.e("BRANCH SDK", "got my Branch link to share: $url")
            } else {
                Log.e("BRANCH SDK", "ERROR WITH BRANCH AF_CHANNEL ")
            }
        })

        campaign.generateShortUrl(context, lp, BranchLinkCreateListener { url, error ->
            if (error == null) {
                Log.e("BRANCH SDK", "got my Branch link to share: $url")
            } else {
                Log.e("BRANCH SDK", "ERROR WITH BRANCH CAMPIOGN ")
            }
        })
    }

    fun test(context: Context) {

        val buo = BranchUniversalObject()
            .setTitle("My Webview Link")
            .setContentDescription("This is a link to my webview content")
            .setCanonicalIdentifier("item/12345")
            .setContentMetadata(ContentMetadata())

        val lp = LinkProperties()
            .setChannel("web")
            .setFeature("deep_links")
            .addControlParameter("\$web_only", "true")


        buo.generateShortUrl(context, lp, BranchLinkCreateListener { url, error ->
            if (error == null) {
                Log.e("BRANCH SDK", "got my Branch link to share: $url", )
            } else {
                Log.e("BRANCH SDK", "ERROR WITH BRANCH BUO ")
            }
        })
    }

}
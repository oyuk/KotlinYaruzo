package com.okysoft.kotlinyaruzo

import android.app.Application
import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import io.fabric.sdk.android.Fabric

/**
 * Created by oyuk on 2016/10/16.
 */
class KotlinYaruzoApplication : Application() {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private val TWITTER_KEY = "D98flQt4zJWNSFLeNSn4RgKJP"
    private val TWITTER_SECRET = "eQc6kwBE0AYlIxduIjIxqGEXw6cHlBNslvWqJWpG7lj7zqiBO3"

    override fun onCreate() {
        super.onCreate()

        val authConfig = TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET)
        Fabric.with(this, Twitter(authConfig))
    }

}
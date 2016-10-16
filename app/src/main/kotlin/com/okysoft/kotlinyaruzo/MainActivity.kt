package com.okysoft.kotlinyaruzo

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.okysoft.kotlinyaruzo.databinding.ActivityMainBinding
import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession


class MainActivity:AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding?.twitterLoginButton?.setCallback(object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                val authToken = Twitter.getInstance().core.sessionManager.activeSession.authToken
                val session = result.data
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                val msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")"
                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
            }

            override  fun failure(exception: TwitterException) {
                Log.d("TwitterKit", "Login with Twitter failure", exception)
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        binding?.twitterLoginButton?.onActivityResult(requestCode, resultCode, data)
    }


}

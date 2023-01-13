package ua.ukma.edu.programistich.kmmtwitter.android

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import ua.ukma.edu.programistich.kmmtwitter.auth.models.TwitterConstants
import ua.ukma.edu.programistich.kmmtwitter.di.initDI

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(
                TwitterAuthConfig(
                    TwitterConstants.consumerApiKey,
                    TwitterConstants.consumerApiSecretKey
                )
            )
            .debug(true)
            .build()
        Twitter.initialize(config)

        initDI {
            androidLogger()
            androidContext(this@Application)
        }
    }
}

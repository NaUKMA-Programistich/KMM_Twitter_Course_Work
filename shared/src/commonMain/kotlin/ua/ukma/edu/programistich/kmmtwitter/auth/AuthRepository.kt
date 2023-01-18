package ua.ukma.edu.programistich.kmmtwitter.auth

import com.appmattus.crypto.Algorithm
import com.russhwolf.settings.Settings
import io.ktor.util.encodeBase64
import kotlinx.datetime.Clock
import okio.ByteString.Companion.encodeUtf8
import ua.ukma.edu.programistich.kmmtwitter.auth.models.TwitterAuthData
import ua.ukma.edu.programistich.kmmtwitter.auth.models.TwitterConstants
import ua.ukma.edu.programistich.kmmtwitter.common.PlatformEncoder

const val ACCESS_TOKEN_KEY = "access_token"
const val ACCESS_SECRET_TOKEN_KEY = "access_secret_token"
const val USER_ID_KEY = "user_id"
const val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"

class AuthRepository {
    private val settings: Settings = Settings()

    fun saveToken(data: TwitterAuthData) {
        settings.putString(ACCESS_TOKEN_KEY, data.accessToken)
        settings.putString(ACCESS_SECRET_TOKEN_KEY, data.accessTokenSecret)
        settings.putString(USER_ID_KEY, data.userId)
    }

    fun isExistToken(): Boolean {
        return settings.getStringOrNull(ACCESS_TOKEN_KEY) != null &&
            settings.getStringOrNull(ACCESS_SECRET_TOKEN_KEY) != null &&
            settings.getStringOrNull(USER_ID_KEY) != null
    }

    fun getOauthToken(
        method: String,
        url: String,
        params: Map<String, String>
    ): String {
        val consumerKey = TwitterConstants.consumerApiKey
        val accessToken = settings.getString(ACCESS_TOKEN_KEY, "")

        val timestamp = Clock.System.now().epochSeconds.toString()
        val nonce = (1..10).map { charset.random() }.joinToString("")

        val authParams = mutableMapOf<String, String>(
            "oauth_consumer_key" to consumerKey,
            "oauth_token" to  accessToken,
            "oauth_signature_method" to "HMAC-SHA1",
            "oauth_version" to "1.0",
            "oauth_timestamp" to timestamp,
            "oauth_nonce" to nonce,
        )
        val signature = generateSignature(method, url, authParams + params);
        authParams["oauth_signature"] = signature

        return authParams
            .map {(key, value) ->
                "${key.encode()}=${value.encode()}"
            }.joinToString(", ")
    }

    private fun generateSignature(
        method: String,
        url: String,
        params: Map<String, String>
    ): String {
        val accessSecret = settings.getString(ACCESS_SECRET_TOKEN_KEY, "")
        val consumerSecret = TwitterConstants.consumerApiSecretKey

        val list = params
            .map { (key, value) ->
                "${key.encode()}=${value.encode()}"
            }
            .sorted()

        val parameters = list.joinToString("&").encode()
        println("parameters: $parameters")
        val signatureBaseString = "$method&${url.encode()}&$parameters"
        val signatureKey = "${consumerSecret.encode()}&${accessSecret.encode()}"

        val signature =
            Algorithm.SHA_1.hmac(
                signatureKey.encodeUtf8().toByteArray(),
                signatureBaseString.encodeUtf8().toByteArray()
            )

        return signature.encodeBase64()
    }

    private fun String.encode(): String {
        return PlatformEncoder.encode(this)
    }
}

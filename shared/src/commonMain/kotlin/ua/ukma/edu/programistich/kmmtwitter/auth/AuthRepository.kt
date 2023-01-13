package ua.ukma.edu.programistich.kmmtwitter.auth

import com.russhwolf.settings.Settings
import ua.ukma.edu.programistich.kmmtwitter.auth.models.TwitterAuthData

const val ACCESS_TOKEN_KEY = "access_token"
const val ACCESS_SECRET_TOKEN_KEY = "access_secret_token"
const val USER_ID_KEY = "user_id"

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
}

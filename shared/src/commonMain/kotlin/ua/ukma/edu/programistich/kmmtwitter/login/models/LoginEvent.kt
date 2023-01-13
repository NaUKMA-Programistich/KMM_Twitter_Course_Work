package ua.ukma.edu.programistich.kmmtwitter.login.models

import ua.ukma.edu.programistich.kmmtwitter.auth.models.TwitterAuthData

sealed class LoginEvent {
    object ClickLogin : LoginEvent()
    data class LoginSuccess(val data: TwitterAuthData?) : LoginEvent()
}

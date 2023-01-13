package ua.ukma.edu.programistich.kmmtwitter.login.models

sealed class LoginState {
    object EntryDisplay : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    object Error : LoginState()
}

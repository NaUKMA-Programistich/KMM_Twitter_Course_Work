package ua.ukma.edu.programistich.kmmtwitter.login.models

sealed class LoginAction {
    object NavigateToAccount : LoginAction()
    object OpenTwitterAuth : LoginAction() // for iOS
}

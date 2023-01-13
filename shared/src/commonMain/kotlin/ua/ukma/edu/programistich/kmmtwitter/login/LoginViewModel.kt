package ua.ukma.edu.programistich.kmmtwitter.login

import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ua.ukma.edu.programistich.kmmtwitter.auth.AuthRepository
import ua.ukma.edu.programistich.kmmtwitter.common.ViewModel
import ua.ukma.edu.programistich.kmmtwitter.login.models.LoginAction
import ua.ukma.edu.programistich.kmmtwitter.login.models.LoginEvent
import ua.ukma.edu.programistich.kmmtwitter.login.models.LoginState

class LoginViewModel : KoinComponent, ViewModel<LoginState, LoginAction, LoginEvent>(
    initialState = LoginState.EntryDisplay
) {
    private val authRepository: AuthRepository by inject()

    override fun obtainEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is LoginEvent.ClickLogin -> {
                withViewModelScope {
                    setViewState(LoginState.Loading)
                    setViewAction(LoginAction.OpenTwitterAuth)
                }
            }
            is LoginEvent.LoginSuccess -> {
                withViewModelScope {
                    val authData = viewEvent.data
                    println("authData: $authData")
                    if (authData != null) {
                        authRepository.saveToken(authData)
                        delay(1000)
                        setViewAction(LoginAction.NavigateToAccount)
                    } else {
                        setViewState(LoginState.Error)
                    }
                }
            }
        }
    }
}

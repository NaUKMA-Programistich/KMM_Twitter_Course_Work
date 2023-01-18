package ua.ukma.edu.programistich.kmmtwitter.splash

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ua.ukma.edu.programistich.kmmtwitter.auth.AuthRepository
import ua.ukma.edu.programistich.kmmtwitter.common.viewmodel.ViewModel

class SplashViewModel : KoinComponent, ViewModel<Unit, Unit, Unit>(
    initialState = Unit
) {
    private val authRepository: AuthRepository by inject()

    override fun obtainEvent(viewEvent: Unit) = Unit

    fun isExistToken() = authRepository.isExistToken()
}

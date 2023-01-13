package ua.ukma.edu.programistich.kmmtwitter.android.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ua.ukma.edu.programistich.kmmtwitter.android.common.WrapperScreen
import ua.ukma.edu.programistich.kmmtwitter.android.navigation.NavigationRoute
import ua.ukma.edu.programistich.kmmtwitter.login.LoginViewModel
import ua.ukma.edu.programistich.kmmtwitter.login.models.LoginAction

@Composable
fun LoginScreen(navController: NavController) {
    WrapperScreen(
        viewModelFactory = { LoginViewModel() },
        onAction = { action -> onAction(action, navController) }
    ) { state, obtainEvent ->
        LoginView(state, obtainEvent)
    }
}

private fun onAction(
    action: LoginAction?,
    navController: NavController
) {
    when (action) {
        LoginAction.NavigateToAccount -> {
            navController.navigate(NavigationRoute.Account.name)
        }
        LoginAction.OpenTwitterAuth -> {}
        null -> {}
    }
}

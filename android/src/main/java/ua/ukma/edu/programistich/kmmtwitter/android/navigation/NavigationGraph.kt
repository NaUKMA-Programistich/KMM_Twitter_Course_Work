package ua.ukma.edu.programistich.kmmtwitter.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.ukma.edu.programistich.kmmtwitter.android.account.AccountScreen
import ua.ukma.edu.programistich.kmmtwitter.android.login.LoginScreen
import ua.ukma.edu.programistich.kmmtwitter.android.main.MainScreen

@Composable
fun NavigationGraph(startRoute: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startRoute) {
        composable(NavigationRoute.Login.name) {
            LoginScreen(navController = navController)
        }
        composable(NavigationRoute.Main.name) {
            MainScreen()
        }
    }
}

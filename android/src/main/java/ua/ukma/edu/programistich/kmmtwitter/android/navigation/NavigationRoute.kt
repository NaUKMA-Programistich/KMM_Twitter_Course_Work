package ua.ukma.edu.programistich.kmmtwitter.android.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationRoute {
    Login,
    Main
}

sealed class NavigationMain(val title: String, val icon: ImageVector) {
    object Account : NavigationMain(title = "Account", icon = Icons.Default.AccountBox)
    object Home : NavigationMain(title = "Home", icon = Icons.Default.Home)

    companion object {
        val allRoutes = listOf(
            NavigationMain.Home,
            NavigationMain.Account
        )
    }
}

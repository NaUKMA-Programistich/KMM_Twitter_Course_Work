package ua.ukma.edu.programistich.kmmtwitter.android.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ua.ukma.edu.programistich.kmmtwitter.android.account.AccountScreen
import ua.ukma.edu.programistich.kmmtwitter.android.home.HomeScreen
import ua.ukma.edu.programistich.kmmtwitter.android.navigation.NavigationMain

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { NavigationBottomBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavHost(navController, startDestination = NavigationMain.Home.title) {
                    composable(NavigationMain.Home.title) {
                        HomeScreen()
                    }
                    composable(NavigationMain.Account.title) {
                        AccountScreen(navController = navController)
                    }
                }
            }
        }
    )
}

@Composable
private fun NavigationBottomBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation {
        NavigationMain.allRoutes.forEach { item ->
            BottomNavigationItem(
                label = { Text(text = item.title) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                alwaysShowLabel = true,
                selected = currentRoute == item.title,
                onClick = {
                    navController.navigate(item.title) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

package ua.ukma.edu.programistich.kmmtwitter.android

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ua.ukma.edu.programistich.kmmtwitter.android.navigation.NavigationGraph
import ua.ukma.edu.programistich.kmmtwitter.android.navigation.NavigationRoute
import ua.ukma.edu.programistich.kmmtwitter.splash.SplashViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val splashViewModel = SplashViewModel()
                val startRoute = splashViewModel
                    .isExistToken()
                    .let { if (it) NavigationRoute.Main else NavigationRoute.Login }

                NavigationGraph(startRoute = startRoute.name)
            }
        }
    }
}

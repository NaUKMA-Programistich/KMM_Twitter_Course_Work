package ua.ukma.edu.programistich.kmmtwitter.android.home

import androidx.compose.runtime.Composable
import ua.ukma.edu.programistich.kmmtwitter.android.common.WrapperScreen
import ua.ukma.edu.programistich.kmmtwitter.home.HomeViewModel

@Composable
fun HomeScreen() {
    WrapperScreen(
        viewModelFactory = { HomeViewModel() },
        onAction = { }
    ) { state, obtainEvent ->
        HomeView(state, obtainEvent)
    }
}

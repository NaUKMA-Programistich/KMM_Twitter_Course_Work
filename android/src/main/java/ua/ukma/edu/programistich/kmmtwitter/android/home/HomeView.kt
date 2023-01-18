package ua.ukma.edu.programistich.kmmtwitter.android.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.ukma.edu.programistich.kmmtwitter.home.dto.Tweet
import ua.ukma.edu.programistich.kmmtwitter.home.models.HomeState

@Composable
internal fun HomeView(
    state: HomeState,
    obtainEvent: (Unit) -> Unit
) {
    when(state) {
        HomeState.Loading -> CircularProgressIndicator()
        is HomeState.Error -> Text(state.error.toString())
        is HomeState.Success -> {
            LazyColumn {
                items(state.tweets) {
                    TweetView(it)
                }
            }
        }
    }
}

@Composable
private fun TweetView(tweet: Tweet) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(tweet.text ?: "")
        Divider()
    }
}
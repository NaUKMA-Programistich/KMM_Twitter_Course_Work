package ua.ukma.edu.programistich.kmmtwitter.home.models

import ua.ukma.edu.programistich.kmmtwitter.home.dto.Tweet

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val tweets: List<Tweet>) : HomeState()
    data class Error(val error: Throwable) : HomeState()
}
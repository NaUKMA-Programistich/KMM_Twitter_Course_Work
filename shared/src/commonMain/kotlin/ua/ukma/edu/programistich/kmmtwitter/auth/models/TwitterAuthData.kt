package ua.ukma.edu.programistich.kmmtwitter.auth.models

data class TwitterAuthData(
    val accessToken: String,
    val accessTokenSecret: String,
    val userId: String
)

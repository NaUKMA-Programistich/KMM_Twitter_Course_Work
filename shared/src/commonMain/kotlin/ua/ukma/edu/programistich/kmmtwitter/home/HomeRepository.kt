package ua.ukma.edu.programistich.kmmtwitter.home

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import ua.ukma.edu.programistich.kmmtwitter.auth.AuthRepository
import ua.ukma.edu.programistich.kmmtwitter.home.dto.Tweet

class HomeRepository(
    private val authRepository: AuthRepository,
    private val httpClient: HttpClient
) {
    suspend fun getHomeTimeline(): List<Tweet> {
        val url = "https://api.twitter.com/1.1/statuses/home_timeline.json"
        val params = mapOf(
            "count" to "200",
            "include_entities" to "true",
            "exclude_replies" to "false",
        )
        val oauth = authRepository.getOauthToken(method = "GET", url, params)
        //val oauth = "oauth_consumer_key=\"AifBYBIYAzUCOGiEfXU0bAT3U\", oauth_token=\"1344662339438706688-Wy9RfP7CAoxFBOxOnE8xehlBwyfR2r\", oauth_signature_method=\"HMAC-SHA1\", oauth_version=\"1.0\", oauth_timestamp=\"1674035921\", oauth_nonce=\"nejwghtmxc\", oauth_signature=\"Gd%2B5Pa5s89%2FlKWB6dZ%2F3GZooKfo%3D\""

        val response = httpClient.get(url) {
            url {
                params.forEach { (key, value) ->
                    parameters.append(key, value)
                }
            }
            headers {
                append(HttpHeaders.Authorization, "OAuth $oauth")
                append(HttpHeaders.Host, "api.twitter.com")
            }
        }
        return response.body()
    }
}
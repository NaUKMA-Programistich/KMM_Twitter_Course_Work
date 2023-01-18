package ua.ukma.edu.programistich.kmmtwitter.common.http

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

internal actual class HttpClientEngine {
    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Android
}
package ua.ukma.edu.programistich.kmmtwitter.common.http

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

internal expect class HttpClientEngine constructor(){
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}
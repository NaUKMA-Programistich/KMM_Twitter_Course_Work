package ua.ukma.edu.programistich.kmmtwitter.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ua.ukma.edu.programistich.kmmtwitter.auth.AuthRepository
import ua.ukma.edu.programistich.kmmtwitter.common.http.HttpClientEngine
import ua.ukma.edu.programistich.kmmtwitter.home.HomeRepository

fun initDI() {
    initDI {}
}

fun initDI(appModule: KoinAppDeclaration = {}) {
    startKoin {
        appModule()
        modules(repositoryModule(), clientModule())
    }
}

fun repositoryModule() = module {
    single { AuthRepository() }
    single { HomeRepository(get(), get()) }
}

fun clientModule(): Module {
    val client = HttpClient(HttpClientEngine().createEngine()) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
    }

    return module {
        single { client }
    }
}

package ua.ukma.edu.programistich.kmmtwitter.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ua.ukma.edu.programistich.kmmtwitter.auth.AuthRepository

fun initDI() {
    initDI {}
}

fun initDI(appModule: KoinAppDeclaration = {}) {
    startKoin {
        appModule()
        modules(repositoryModule())
    }
}

fun repositoryModule() = module {
    single { AuthRepository() }
}

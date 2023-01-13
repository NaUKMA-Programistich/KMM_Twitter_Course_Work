package ua.ukma.edu.programistich.kmmtwitter.common.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class KMMFlow<T> : Flow<T> {
    fun observe(block: (T) -> Unit): Cancelable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        onEach {
            block(it)
        }.launchIn(scope)

        return object : Cancelable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }
}


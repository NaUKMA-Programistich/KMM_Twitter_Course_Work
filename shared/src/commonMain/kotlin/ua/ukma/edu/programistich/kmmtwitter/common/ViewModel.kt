package ua.ukma.edu.programistich.kmmtwitter.common

import com.adeo.kviewmodel.KViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ua.ukma.edu.programistich.kmmtwitter.common.flow.Cancelable
import ua.ukma.edu.programistich.kmmtwitter.common.flow.KMMSharedFlow
import ua.ukma.edu.programistich.kmmtwitter.common.flow.KMMStateFlow

abstract class ViewModel<State, Action, Event>(initialState: State) : KViewModel() {
    private val _viewStates = MutableStateFlow(initialState)
    fun viewStates() = KMMStateFlow(_viewStates.asStateFlow())
    suspend fun setViewState(state: State) {
        _viewStates.emit(state)
    }

    private val _viewActions = MutableSharedFlow<Action?>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    fun viewActions() = KMMSharedFlow(_viewActions.asSharedFlow()) {
        withViewModelScope { _viewActions.emit(null) }
    }
    suspend fun setViewAction(action: Action) {
        _viewActions.emit(action)
    }

    abstract fun obtainEvent(viewEvent: Event)
    fun clearAction() {
        viewModelScope.launch {
            _viewActions.emit(null)
        }
    }

    fun withViewModelScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(block = block)
    }

    fun onChangeState(block: (State) -> Unit): Cancelable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        _viewStates.onEach {
            block(it)
        }.launchIn(scope)

        return object : Cancelable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }

    fun onChangeAction(block: (Action?) -> Unit): Cancelable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        _viewActions.onEach {
            block(it)
        }.launchIn(scope)

        return object : Cancelable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }
}

package ua.ukma.edu.programistich.kmmtwitter.android.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import ua.ukma.edu.programistich.kmmtwitter.common.viewmodel.ViewModel

@Composable
fun <T : ViewModel<State, Action, Event>, State, Action, Event> WrapperScreen(
    viewModelFactory: @DisallowComposableCalls () -> T,
    onAction: (Action?) -> Unit,
    content: @Composable (State, (Event) -> Unit) -> Unit
) {
    val viewModel = remember { viewModelFactory() }
    val viewState = viewModel.viewStates().collectAsState()
    val viewAction = viewModel.viewActions().collectAsState(initial = null)
    val obtainEvent = { event: Event ->
        viewModel.obtainEvent(event)
    }

    onAction(viewAction.value)

    content(viewState.value) {
        obtainEvent(it)
    }

    DisposableEffect(Unit) {
        onDispose(viewModel::clear)
    }
}
